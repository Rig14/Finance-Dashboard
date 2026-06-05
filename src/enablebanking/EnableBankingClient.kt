package enablebanking

import klite.http.httpClient
import klite.json.JsonHttpClient
import klite.json.JsonMapper
import java.net.URI
import java.time.Instant
import java.util.*

class EnableBankingClient {
  private val http = JsonHttpClient(
    "https://api.enablebanking.com",
    reqModifier = { setHeader("Authorization", "Bearer ${Jwt.createJwt()}")},
    http = httpClient(), json = JsonMapper())

  suspend fun initiateAuth(): AuthorizationResponse {
    val validUntil = Instant.now().plusSeconds(10 * 24 * 60 * 60)
    val body = AuthorizationRequest(
      Access(validUntil),
      Aspsp("LHV Pank", "EE"),
      state = UUID.randomUUID(),
      redirectUrl = URI("https://rivis.ee"),
      psuType = "personal"
    )

    return http.post<AuthorizationResponse>("/auth", body)
  }
}
