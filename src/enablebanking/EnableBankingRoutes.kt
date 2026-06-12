package enablebanking

import auth.Access
import klite.annotations.AttrParam
import klite.annotations.GET
import klite.annotations.Path
import klite.annotations.QueryParam
import users.User

@Path("/enablebanking")
@Access
class EnableBankingRoutes(private val client: EnableBankingClient) {
  @GET("/auth") suspend fun auth(@QueryParam bankName: String, @QueryParam country: CountryCode) = client.initiateAuth(bankName, country)
  @GET("/banks") suspend fun listBanks(@QueryParam country: CountryCode) = client.listBanks(country)
  @GET("/session") suspend fun session(@QueryParam code: String, @AttrParam user: User) = client.createSession(code, user)
}
