package transactions

import auth.Access
import enablebanking.EnableBankingClient
import klite.BadRequestException
import klite.annotations.AttrParam
import klite.annotations.GET
import klite.annotations.Path
import users.User

@Access
@Path("/transactions")
class TransactionsRoutes(private val bankClient: EnableBankingClient) {
  @GET("/refresh") suspend fun updateTransactions(@AttrParam user: User) {
    if (user.sessionId == null) throw BadRequestException("No session")
    val sessionData = bankClient.sessionsData(user.sessionId)

    for (account in sessionData.accounts) {
      val transactions = bankClient.transactions(account)

    }

  }
}

