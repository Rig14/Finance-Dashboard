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
      redirectUrl = URI("http://localhost:8000/session"),
      psuType = "personal"
    )

    return http.post<AuthorizationResponse>("/auth", body)
  }

  suspend fun createSession(code: UUID): CreateSessionResponse {
    val body = CreateSessionRequest(code)

    return http.post<CreateSessionResponse>("/session", body)
  }
}
