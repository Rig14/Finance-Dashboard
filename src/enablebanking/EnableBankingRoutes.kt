package enablebanking

import klite.annotations.GET
import klite.annotations.Path

@Path("/enable-banking")
class EnableBankingRoutes(private val client: EnableBankingClient) {
  @GET("/auth") suspend fun auth(): AuthorizationResponse = client.initiateAuth()
}
