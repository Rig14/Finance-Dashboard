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
  @GET("/auth") suspend fun auth() = client.initiateAuth()
  @GET("/banks") suspend fun listBanks(@QueryParam countryCode: CountryCode) = client.listBanks(countryCode)
  @GET("/session") suspend fun session(@QueryParam code: String, @AttrParam user: User) = client.createSession(code, user)
}
