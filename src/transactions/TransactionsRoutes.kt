package transactions

import auth.Access
import enablebanking.EnableBankingClient
import klite.BadRequestException
import klite.annotations.AttrParam
import klite.annotations.POST
import users.User

@Access
class TransactionsRoutes(private val bankClient: EnableBankingClient) {
  @POST suspend fun updateTransactions(@AttrParam user: User) {
    if (user.sessionId == null) throw BadRequestException("No session")
    val sessionData = bankClient.sessionsData(user.sessionId)

    for (account in sessionData.accounts) {
      val transactions = bankClient.transactions(account)

      for (transaction in transactions) {

      }
    }
  }
}

