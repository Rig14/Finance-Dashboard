package transactions

import auth.Access
import enablebanking.EnableBankingClient
import klite.BadRequestException
import klite.Decimal
import klite.annotations.AttrParam
import klite.annotations.GET
import klite.annotations.Path
import users.User

@Access
@Path("/transactions")
class TransactionsRoutes(
  private val bankClient: EnableBankingClient,
  private val transactionsRepository: TransactionsRepository
) {
  @GET("/refresh") suspend fun updateTransactions(@AttrParam user: User) {
    if (user.sessionId == null) throw BadRequestException("No session")
    val sessionData = bankClient.sessionsData(user.sessionId)

    for (accountId in sessionData.accounts) {
      val transactions = bankClient.transactions(accountId)

      transactions.forEach {
        transactionsRepository.save(Transaction(
          amount = Decimal(it.transactionAmount.amount),
          creditor = it.creditor?.name,
          creditDebitIndicator = it.creditDebitIndicator,
          date = it.bookingDate,
          accountId = accountId,
          userId = user.id,
          categoryCode = it.merchantCategoryCode,
          note = it.note + it.remittanceInformation,
          hashCode = it.hashCode()
        ))
      }
    }

  }
}

