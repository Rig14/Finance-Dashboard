package enablebanking

import klite.annotations.AttrParam
import klite.annotations.GET
import klite.annotations.QueryParam
import users.User

class EnableBankingRoutes(private val client: EnableBankingClient) {
  @GET("/auth") suspend fun auth() = client.initiateAuth()
  @GET("/session") suspend fun session(@QueryParam code: String, @AttrParam user: User) = client.createSession(code, user)
}
