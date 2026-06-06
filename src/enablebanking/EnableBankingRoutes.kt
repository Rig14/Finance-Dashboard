package enablebanking

import klite.annotations.GET
import klite.annotations.QueryParam

class EnableBankingRoutes(private val client: EnableBankingClient) {
  @GET("/auth") suspend fun auth(): StartAuthorizationResponse = client.initiateAuth()
  @GET("/session") suspend fun session(@QueryParam code: String): AuthorizeSessionResponse = client.createSession(code)
}
