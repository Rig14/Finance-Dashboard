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

data class CreateSessionRequest(
  val code: UUID
)


data class CreateSessionResponse(
  @JsonProperty("session_id") val sessionId: String,
  val accounts: List<Account>,
  val aspsp: Aspsp,
  @JsonProperty("psu_type") val psuType: String,
  val access: Access
)

data class Account(
  @JsonProperty("account_id") val accountId: AccountId,
  @JsonProperty("all_account_ids") val allAccountIds: List<AllAccountId>,
  @JsonProperty("account_servicer") val accountServicer: AccountServicer,
  val name: String,
  val details: String,
  val usage: String,
  @JsonProperty("cash_account_type") val cashAccountType: String,
  val product: String,
  val currency: String,
  @JsonProperty("psu_status") val psuStatus: String,
  @JsonProperty("credit_limit") val creditLimit: CreditLimit,
  @JsonProperty("legal_age") val legalAge: Boolean,
  @JsonProperty("postal_address") val postalAddress: PostalAddress,
  val uid: UUID,
  @JsonProperty("identification_hash") val identificationHash: String,
  @JsonProperty("identification_hashes") val identificationHashes: List<String>
)

data class AccountId(
  val iban: String
)

data class AllAccountId(
  val identification: String,
  @JsonProperty("scheme_name") val schemeName: String
)

data class AccountServicer(
  @JsonProperty("bic_fi") val bicFi: String,
  @JsonProperty("clearing_system_member_id") val clearingSystemMemberId: ClearingSystemMemberId,
  val name: String
)

data class ClearingSystemMemberId(
  @JsonProperty("clearing_system_id") val clearingSystemId: String,
  @JsonProperty("member_id") val memberId: Int
)

data class CreditLimit(
  val currency: String,
  val amount: String
)

data class PostalAddress(
  @JsonProperty("address_type") val addressType: String,
  val department: String,
  @JsonProperty("sub_department") val subDepartment: String,
  @JsonProperty("street_name") val streetName: String,
  @JsonProperty("building_number") val buildingNumber: String,
  @JsonProperty("post_code") val postCode: String,
  @JsonProperty("town_name") val townName: String,
  @JsonProperty("country_sub_division") val countrySubDivision: String,
  val country: String,
  @JsonProperty("address_line") val addressLine: List<String>
)

