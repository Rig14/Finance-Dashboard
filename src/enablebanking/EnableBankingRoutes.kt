package enablebanking

import klite.annotations.GET
import klite.annotations.QueryParam
import java.util.*

class EnableBankingRoutes(private val client: EnableBankingClient) {
  @GET("/auth") suspend fun auth(): AuthorizationResponse = client.initiateAuth()

  @GET("/session") suspend fun session(@QueryParam code: UUID): CreateSessionResponse {
    return client.createSession(code)
  }
}
