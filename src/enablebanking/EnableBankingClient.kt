package enablebanking

import klite.http.httpClient
import klite.json.JsonHttpClient
import klite.json.JsonMapper
import klite.urlEncodeParams
import users.Jwt
import users.Payload
import users.User
import users.UserRepository
import java.net.URI
import java.time.Instant
import java.time.LocalDate
import java.util.*

class EnableBankingClient(
  private val userRepository: UserRepository,
) {
  private val http = JsonHttpClient("https://api.enablebanking.com",
    reqModifier = { setHeader("Authorization", "Bearer ${Jwt.create(Payload("enablebanking.com", "api.enablebanking.com"))}")},
    http = httpClient(), json = JsonMapper())

  suspend fun initiateAuth(bankName: String, country: CountryCode): StartAuthorizationResponse {
    val validUntil = Instant.now().plusSeconds(10 * 24 * 60 * 60)
    val body = StartAuthorizationRequest(
      aspsp = ASPSP(bankName, country),
      access = Access(validUntil = validUntil),
      state = UUID.randomUUID().toString(),
      redirectUrl = URI("http://localhost:8000/session")
    )

    return http.post<StartAuthorizationResponse>("/auth", body)
  }

  suspend fun createSession(code: String, user: User): AuthorizeSessionResponse {
    val session = http.post<AuthorizeSessionResponse>("/sessions", AuthorizeSessionRequest(code))

    userRepository.update(user.copy(sessionId = session.sessionId))
    return session
  }

  suspend fun sessionsData(sessionId: UUID) = http.get<GetSessionResponse>("/sessions/$sessionId")
  suspend fun transactions(accountId: UUID, dateRange: ClosedRange<LocalDate> = LocalDate.now().minusMonths(1)..LocalDate.now()): List<EnableBankingTransaction> {
    val result = mutableListOf<EnableBankingTransaction>()
    var key: String? = null

    do {
      val query = urlEncodeParams(mapOf("continuation_key" to key, "date_from" to dateRange.start, "date_to" to dateRange.endInclusive))
      val response = http.get<HalTransactions>("/accounts/$accountId/transactions?$query")

      result.addAll(response.transactions)
      key = response.continuationKey
    } while (key != null)

    return result
  }

  suspend fun listBanks(country: CountryCode) = http.get<GetAspspsResponse>("/aspsps?country=$country")
}
