package transactions

import auth.Access
import enablebanking.EnableBankingClient
import klite.BadRequestException
import klite.Decimal
import klite.annotations.AttrParam
import klite.annotations.GET
import klite.annotations.Path
import klite.error
import klite.jdbc.AlreadyExistsException
import klite.logger
import users.User

@Access
@Path("/transactions")
class TransactionsRoutes(
  private val bankClient: EnableBankingClient,
  private val transactionsRepository: TransactionsRepository
) {
  private val log = logger()

  @GET("/refresh") suspend fun updateTransactions(@AttrParam user: User): Map<String, Int> {
    if (user.sessionId == null) throw BadRequestException("No session")
    val sessionData = bankClient.sessionsData(user.sessionId)

    var importedTransactionCount = 0
    var skippedTransactionCount = 0

    for (accountId in sessionData.accounts) {
      val transactions = bankClient.transactions(accountId)

      transactions.forEach {
        try {
          transactionsRepository.save(Transaction(
            amount = Decimal(it.transactionAmount.amount),
            creditor = it.creditor?.name,
            creditDebitIndicator = it.creditDebitIndicator,
            entryReference = it.entryReference ?: it.hashCode().toString(),
            date = it.bookingDate,
            accountId = accountId,
            userId = user.id,
            categoryCode = it.merchantCategoryCode,
            note = "note: ${it.note}\n\nremittance information: ${it.remittanceInformation?.joinToString()}",
          ))
          importedTransactionCount++
        } catch (e: Exception) {
          if (e !is AlreadyExistsException) log.error("Error inserting transaction", e)
          skippedTransactionCount++
        }
      }
    }

    return mapOf("imported" to importedTransactionCount, "skipped" to skippedTransactionCount)
  }
}

