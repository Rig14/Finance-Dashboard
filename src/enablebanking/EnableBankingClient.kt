package enablebanking

import klite.http.httpClient
import klite.json.JsonHttpClient
import klite.json.JsonMapper
import java.net.URI
import java.time.Instant
import java.util.*

class EnableBankingClient {
  private val http = JsonHttpClient("https://api.enablebanking.com",
    reqModifier = { setHeader("Authorization", "Bearer ${Jwt.createJwt()}")},
    http = httpClient(), json = JsonMapper())

  suspend fun initiateAuth(): StartAuthorizationResponse {
    val validUntil = Instant.now().plusSeconds(10 * 24 * 60 * 60)
    val body = StartAuthorizationRequest(
      aspsp = ASPSP("LHV Pank", "EE"),
      access = Access(validUntil = validUntil),
      state = UUID.randomUUID().toString(),
      redirectUrl = URI("http://localhost:8000/session")
    )

    return http.post<StartAuthorizationResponse>("/auth", body)
  }

  suspend fun createSession(code: String): AuthorizeSessionResponse {
    val body = AuthorizeSessionRequest(code)

    return http.post<AuthorizeSessionResponse>("/sessions", body)
  }
}
