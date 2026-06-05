package enablebanking

import klite.json.JsonProperty
import java.net.URI
import java.time.Instant
import java.util.*


data class AuthorizationRequest(
  val access: Access,
  val aspsp: Aspsp,
  val state: UUID,
  @JsonProperty("redirect_url") val redirectUrl: URI,
  @JsonProperty("psu_type") val psuType: String
)

data class Access(
  @JsonProperty("valid_until") val validUntil: Instant
)

data class Aspsp(
  val name: String,
  val country: String
)

data class AuthorizationResponse(
  val url: URI,
  @JsonProperty("authorization_id") val authorizationId: UUID,
  @JsonProperty("psu_id_hash") val psuIdHash: String,
)

