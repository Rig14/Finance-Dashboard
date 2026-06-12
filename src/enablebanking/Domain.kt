package enablebanking

import klite.json.JsonProperty
import java.net.URI
import java.time.Instant
import java.time.LocalDate
import java.util.*

data class ASPSP(
  val name: String,
  val country: String
)

data class ASPSPData(
  val name: String,
  val country: String,
  val logo: URI,
  @JsonProperty("psu_types") val psuTypes: List<PSUType>,
  @JsonProperty("auth_methods") val authMethods: List<AuthMethod>,
  @JsonProperty("maximum_consent_validity") val maximumConsentValidity: Int,
  val sandbox: SandboxInfo? = null,
  val beta: Boolean,
  val bic: String? = null,
  @JsonProperty("required_psu_headers") val requiredPsuHeaders: List<String>? = null,
  val payments: List<ResponsePaymentType>? = null,
  val group: ASPSPGroup? = null
)

data class ASPSPGroup(
  val name: String,
  val logo: URI
)

data class Access(
  val accounts: List<AccountIdentification>? = null,
  val balances: Boolean? = null,
  val transactions: Boolean? = null,
  @JsonProperty("valid_until") val validUntil: Instant
)

data class AccountIdentification(
  val iban: String? = null,
  val other: GenericIdentification? = null
)

data class AccountResource(
  @JsonProperty("account_id") val accountId: AccountIdentification? = null,
  @JsonProperty("all_account_ids") val allAccountIds: List<GenericIdentification>? = null,
  @JsonProperty("account_servicer") val accountServicer: FinancialInstitutionIdentification? = null,
  val name: String? = null,
  val details: String? = null,
  val usage: Usage? = null,
  @JsonProperty("cash_account_type") val cashAccountType: CashAccountType? = null,
  val product: String? = null,
  val currency: String,
  @JsonProperty("psu_status") val psuStatus: String? = null,
  @JsonProperty("credit_limit") val creditLimit: AmountType? = null,
  @JsonProperty("legal_age") val legalAge: Boolean? = null,
  @JsonProperty("postal_address") val postalAddress: PostalAddress? = null,
  val uid: UUID? = null,
  @JsonProperty("identification_hash") val identificationHash: String,
  @JsonProperty("identification_hashes") val identificationHashes: List<String>
)

enum class AddressType {
  @JsonProperty("Business") BUSINESS,
  @JsonProperty("Correspondence") CORRESPONDENCE,
  @JsonProperty("Delivery To") DELIVERY_TO,
  @JsonProperty("MailTo") MAIL_TO,
  @JsonProperty("POBox") PO_BOX,
  @JsonProperty("Postal") POSTAL,
  @JsonProperty("Residential") RESIDENTIAL,
  @JsonProperty("Statement") STATEMENT
}

data class AmountType(
  val currency: String,
  val amount: String
)

data class AuthMethod(
  val name: String? = null,
  val title: String? = null,
  @JsonProperty("psu_type") val psuType: PSUType,
  val credentials: List<Credential>? = null,
  val approach: AuthenticationApproach,
  @JsonProperty("hidden_method") val hiddenMethod: Boolean
)

enum class AuthenticationApproach {
  DECOUPLED, EMBEDDED, REDIRECT
}

data class AuthorizeSessionRequest(
  val code: String
)

data class AuthorizeSessionResponse(
  @JsonProperty("session_id") val sessionId: UUID,
  val accounts: List<AccountResource>,
  val aspsp: ASPSP,
  @JsonProperty("psu_type") val psuType: PSUType,
  val access: Access
)

data class BalanceResource(
  val name: String,
  @JsonProperty("balance_amount") val balanceAmount: AmountType,
  @JsonProperty("balance_type") val balanceType: BalanceStatus,
  @JsonProperty("last_change_date_time") val lastChangeDateTime: Instant? = null,
  @JsonProperty("reference_date") val referenceDate: LocalDate? = null,
  @JsonProperty("last_committed_transaction") val lastCommittedTransaction: String? = null
)

enum class BalanceStatus {
  CLAV, CLBD, FWAV, INFO, ITAV, ITBD, OPAV, OPBD, OTHR, PRCD, VALU, XPCD
}

data class BankTransactionCode(
  val description: String? = null,
  val code: String? = null,
  @JsonProperty("sub_code") val subCode: String? = null
)

data class Beneficiary(
  @JsonProperty("creditor_agent") val creditorAgent: FinancialInstitutionIdentification? = null,
  val creditor: PartyIdentification? = null,
  @JsonProperty("creditor_account") val creditorAccount: GenericIdentification,
  @JsonProperty("creditor_currency") val creditorCurrency: String? = null
)

enum class CashAccountType {
  CACC, CARD, CASH, LOAN, OTHR, SVGS
}

enum class CategoryPurposeCode {
  BONU, CASH, CBLK, CCRD, CORT, DCRD, DIVI, DVPM, EPAY, FCOL, GOVT, HEDG, ICCP, IDCP, INTC, INTE, LOAN, MP2B, MP2P, OTHR, PENS, RPRE, RRCT, RVPM, SALA, SECU, SSBE, SUPP, TAXS, TRAD, TREA, VATX, WHLD
}

enum class ChargeBearerCode {
  CRED, DEBT, SHAR, SLEV
}

data class ClearingSystemMemberIdentification(
  @JsonProperty("clearing_system_id") val clearingSystemId: String? = null,
  @JsonProperty("member_id") val memberId: String? = null
)

data class ContactDetails(
  @JsonProperty("email_address") val emailAddress: String? = null,
  @JsonProperty("phone_number") val phoneNumber: String? = null
)

data class CreatePaymentRequest(
  @JsonProperty("payment_type") val paymentType: PaymentType,
  @JsonProperty("payment_request") val paymentRequest: PaymentRequestResource,
  val aspsp: ASPSP,
  val state: String,
  @JsonProperty("redirect_url") val redirectUrl: URI,
  @JsonProperty("psu_type") val psuType: PSUType,
  @JsonProperty("auth_method") val authMethod: String? = null,
  val credentials: Any? = null,
  val language: String? = null,
  @JsonProperty("webhook_url") val webhookUrl: URI? = null,
  @JsonProperty("psu_id") val psuId: String? = null,
  @JsonProperty("defer_submission") val deferSubmission: Boolean? = null
)

data class CreatePaymentResponse(
  @JsonProperty("payment_id") val paymentId: UUID,
  val status: PaymentStatus,
  val url: URI,
  @JsonProperty("psu_id_hash") val psuIdHash: String
)

data class Credential(
  val name: String,
  val title: String,
  val required: Boolean,
  val description: String? = null,
  val template: String? = null
)

enum class CreditDebitIndicator {
  CRDT, DBIT
}

data class CreditTransferTransaction(
  @JsonProperty("instructed_amount") val instructedAmount: AmountType,
  val beneficiary: Beneficiary,
  @JsonProperty("payment_id") val paymentId: PaymentIdentification? = null,
  @JsonProperty("requested_execution_date") val requestedExecutionDate: LocalDate? = null,
  @JsonProperty("reference_number") val referenceNumber: String? = null,
  @JsonProperty("end_date") val endDate: LocalDate? = null,
  @JsonProperty("execution_rule") val executionRule: ExecutionRule? = null,
  val frequency: FrequencyCode? = null,
  @JsonProperty("ultimate_debtor") val ultimateDebtor: PartyIdentification? = null,
  @JsonProperty("ultimate_creditor") val ultimateCreditor: PartyIdentification? = null,
  @JsonProperty("regulatory_reporting") val regulatoryReporting: List<RegulatoryReporting>? = null,
  @JsonProperty("remittance_information") val remittanceInformation: List<String>? = null
)

data class CreditTransferTransactionDetails(
  @JsonProperty("instructed_amount") val instructedAmount: AmountType,
  val beneficiary: Beneficiary,
  @JsonProperty("payment_id") val paymentId: PaymentIdentification? = null,
  @JsonProperty("requested_execution_date") val requestedExecutionDate: LocalDate? = null,
  @JsonProperty("reference_number") val referenceNumber: String? = null,
  @JsonProperty("end_date") val endDate: LocalDate? = null,
  @JsonProperty("execution_rule") val executionRule: ExecutionRule? = null,
  val frequency: FrequencyCode? = null,
  @JsonProperty("ultimate_debtor") val ultimateDebtor: PartyIdentification? = null,
  @JsonProperty("ultimate_creditor") val ultimateCreditor: PartyIdentification? = null,
  @JsonProperty("regulatory_reporting") val regulatoryReporting: List<RegulatoryReporting>? = null,
  @JsonProperty("remittance_information") val remittanceInformation: List<String>? = null,
  @JsonProperty("transaction_id") val transactionId: String? = null,
  @JsonProperty("transaction_status") val transactionStatus: PaymentStatus? = null
)

enum class Environment {
  PRODUCTION, SANDBOX
}

enum class ErrorCode {
  ACCESS_DENIED, ACCOUNT_DOES_NOT_EXIST, ALREADY_AUTHORIZED, ASPSP_ACCOUNT_NOT_ACCESSIBLE,
  ASPSP_ERROR, ASPSP_PAYMENT_NOT_ACCESSIBLE, ASPSP_PSU_ACTION_REQUIRED, ASPSP_RATE_LIMIT_EXCEEDED,
  ASPSP_TIMEOUT, AUTHORIZATION_NOT_PROVIDED, CLOSED_SESSION, DATE_FROM_IN_FUTURE,
  DATE_TO_WITHOUT_DATE_FROM, EXPIRED_AUTHORIZATION_CODE, EXPIRED_SESSION, INVALID_ACCOUNT_ID,
  INVALID_HOST, INVALID_PAYMENT, NO_ACCOUNTS_ADDED, PAYMENT_LIMIT_EXCEEDED, PAYMENT_NOT_AUTHORIZED,
  PAYMENT_NOT_FINALIZED, PAYMENT_NOT_FOUND, PAYMENT_SUBMISSION_NOT_DEFERRED,
  PAYMENT_SUBMISSION_NOT_SUPPORTED, PSU_HEADER_INVALID, PSU_HEADER_NOT_PROVIDED,
  REDIRECT_URI_NOT_ALLOWED, REVOKED_SESSION, SESSION_DOES_NOT_EXIST, TRANSACTION_DOES_NOT_EXIST,
  UNAUTHORIZED_ACCESS, UNAUTHORIZED_IP, UNTRUSTED_PAYMENT_PARTY, WEBHOOK_URI_NOT_ALLOWED,
  WRONG_ASPSP_PROVIDED, WRONG_AUTHORIZATION_CODE, WRONG_CONTINUATION_KEY, WRONG_CREDENTIALS_PROVIDED,
  WRONG_DATE_INTERVAL, WRONG_REQUEST_PARAMETERS, WRONG_SESSION_STATUS, WRONG_TRANSACTIONS_PERIOD
}

data class ErrorResponse(
  val message: String,
  val code: Int? = null,
  val error: ErrorCode? = null,
  val detail: Any? = null
)

data class ExchangeRate(
  @JsonProperty("unit_currency") val unitCurrency: String? = null,
  @JsonProperty("exchange_rate") val exchangeRate: String? = null,
  @JsonProperty("rate_type") val rateType: RateType? = null,
  @JsonProperty("contract_identification") val contractIdentification: String? = null,
  @JsonProperty("instructed_amount") val instructedAmount: AmountType? = null
)

enum class ExecutionRule {
  FWNG, PREC
}

data class FinancialInstitutionIdentification(
  @JsonProperty("bic_fi") val bicFi: String? = null,
  @JsonProperty("clearing_system_member_id") val clearingSystemMemberId: ClearingSystemMemberIdentification? = null,
  val name: String? = null
)

enum class FrequencyCode {
  DAIL, MNTH, QUTR, SEMI, TOMN, TOWK, WEEK, YEAR
}

data class GenericIdentification(
  val identification: String,
  @JsonProperty("scheme_name") val schemeName: SchemeName,
  val issuer: String? = null
)

data class GetApplicationResponse(
  val name: String,
  val description: String? = null,
  val kid: UUID,
  val environment: Environment,
  @JsonProperty("redirect_urls") val redirectUrls: List<String>,
  val active: Boolean,
  val countries: List<String>,
  val services: List<Service>
)

data class GetAspspsResponse(
  val aspsps: List<ASPSPData>
)

data class GetPaymentResponse(
  @JsonProperty("payment_id") val paymentId: UUID,
  val status: PaymentStatus,
  @JsonProperty("payment_details") val paymentDetails: PaymentRequestResourceDetails,
  @JsonProperty("payment_type") val paymentType: PaymentType,
  val aspsp: ASPSP,
  @JsonProperty("final_status") val finalStatus: Boolean,
  @JsonProperty("status_reason_information") val statusReasonInformation: StatusReasonInformation? = null,
  @JsonProperty("psu_id_hash") val psuIdHash: String
)

data class GetPaymentTransactionResponse(
  @JsonProperty("payment_id") val paymentId: UUID,
  @JsonProperty("transaction_details") val transactionDetails: CreditTransferTransactionDetails
)

data class GetSessionResponse(
  val status: SessionStatus,
  val accounts: List<String>,
  @JsonProperty("accounts_data") val accountsData: List<SessionAccount>,
  val aspsp: ASPSP,
  @JsonProperty("psu_type") val psuType: PSUType,
  @JsonProperty("psu_id_hash") val psuIdHash: String,
  val access: Access,
  val created: Instant,
  val authorized: Instant? = null,
  val closed: Instant? = null
)

data class HalBalances(
  val balances: List<BalanceResource>
)

data class HalTransactions(
  val transactions: List<Transaction>,
  @JsonProperty("continuation_key") val continuationKey: String? = null
)

enum class PSUType {
  @JsonProperty("business") BUSINESS,
  @JsonProperty("personal") PERSONAL
}

data class PartyIdentification(
  val name: String? = null,
  @JsonProperty("postal_address") val postalAddress: PostalAddress? = null,
  @JsonProperty("organisation_id") val organisationId: GenericIdentification? = null,
  @JsonProperty("private_id") val privateId: GenericIdentification? = null,
  @JsonProperty("contact_details") val contactDetails: ContactDetails? = null
)

data class PaymentIdentification(
  @JsonProperty("instruction_id") val instructionId: String? = null,
  @JsonProperty("end_to_end_id") val endToEndId: String? = null
)

data class PaymentRequestResource(
  @JsonProperty("payment_information_id") val paymentInformationId: String? = null,
  @JsonProperty("payment_type_information") val paymentTypeInformation: PaymentTypeInformation? = null,
  val debtor: PartyIdentification? = null,
  @JsonProperty("debtor_account") val debtorAccount: GenericIdentification? = null,
  @JsonProperty("debtor_agent") val debtorAgent: FinancialInstitutionIdentification? = null,
  @JsonProperty("debtor_currency") val debtorCurrency: String? = null,
  val purpose: PurposeCode? = null,
  @JsonProperty("charge_bearer") val chargeBearer: ChargeBearerCode? = null,
  @JsonProperty("credit_transfer_transaction") val creditTransferTransaction: List<CreditTransferTransaction>
)

data class PaymentRequestResourceDetails(
  @JsonProperty("payment_information_id") val paymentInformationId: String? = null,
  @JsonProperty("payment_type_information") val paymentTypeInformation: PaymentTypeInformation? = null,
  val debtor: PartyIdentification? = null,
  @JsonProperty("debtor_account") val debtorAccount: GenericIdentification? = null,
  @JsonProperty("debtor_agent") val debtorAgent: FinancialInstitutionIdentification? = null,
  @JsonProperty("debtor_currency") val debtorCurrency: String? = null,
  val purpose: PurposeCode? = null,
  @JsonProperty("charge_bearer") val chargeBearer: ChargeBearerCode? = null,
  @JsonProperty("credit_transfer_transaction") val creditTransferTransaction: List<CreditTransferTransactionDetails>? = null
)

enum class PaymentStatus {
  ACCC, ACCP, ACCR, ACPT, ACSC, ACSP, ACTC, ACWC, ACWP, CNCL, NULL, PACR, PART, PDCR, PDNG, RCVD, RJCR, RJCT
}

enum class PaymentType {
  @JsonProperty("BULK DOMESTIC") BULK_DOMESTIC,
  @JsonProperty("BULK DOMESTIC_SE_GIRO") BULK_DOMESTIC_SE_GIRO,
  BULK_SEPA, CROSSBORDER, DOMESTIC, DOMESTIC_SE_GIRO, INST_SEPA, INTERNAL, SEPA
}

data class PaymentTypeInformation(
  @JsonProperty("instruction_priority") val instructionPriority: PriorityCode? = null,
  @JsonProperty("service_level") val serviceLevel: ServiceLevelCode? = null,
  @JsonProperty("category_purpose") val categoryPurpose: CategoryPurposeCode? = null,
  @JsonProperty("local_instrument") val localInstrument: String? = null
)

data class PostalAddress(
  @JsonProperty("address_type") val addressType: AddressType? = null,
  val department: String? = null,
  @JsonProperty("sub_department") val subDepartment: String? = null,
  @JsonProperty("street_name") val streetName: String? = null,
  @JsonProperty("building_number") val buildingNumber: String? = null,
  @JsonProperty("post_code") val postCode: String? = null,
  @JsonProperty("town_name") val townName: String? = null,
  @JsonProperty("country_sub_division") val countrySubDivision: String? = null,
  val country: String? = null,
  @JsonProperty("address_line") val addressLine: List<String>? = null
)

enum class PriorityCode {
  EXPR, HIGH, NORM
}

enum class PurposeCode {
  ACCT, CASH, COMC, CPKC, TRPT
}

enum class RateType {
  AGRD, SALE, SPOT
}

enum class ReferenceNumberScheme {
  BERF, FIRF, INTL, NORF, SDDM, SEBG
}

data class RegulatoryAuthority(
  val country: String,
  val name: String
)

data class RegulatoryReporting(
  val authority: RegulatoryAuthority? = null,
  val details: RegulatoryReportingDetails
)

data class RegulatoryReportingCode(
  val value: String,
  val description: String
)

data class RegulatoryReportingDetails(
  val amount: AmountType? = null,
  val code: String? = null,
  val information: String
)

data class RemittanceInformationLineInfo(
  @JsonProperty("min_length") val minLength: Int? = null,
  @JsonProperty("max_length") val maxLength: Int? = null,
  val pattern: String? = null
)

data class ResponsePaymentType(
  @JsonProperty("payment_type") val paymentType: PaymentType? = null,
  @JsonProperty("max_transactions") val maxTransactions: Int? = null,
  val currencies: List<String>? = null,
  @JsonProperty("debtor_account_required") val debtorAccountRequired: Boolean? = null,
  @JsonProperty("debtor_account_schemas") val debtorAccountSchemas: List<SchemeName>? = null,
  @JsonProperty("creditor_account_schemas") val creditorAccountSchemas: List<SchemeName>? = null,
  @JsonProperty("priority_codes") val priorityCodes: List<PriorityCode>? = null,
  @JsonProperty("creditor_country_required") val creditorCountryRequired: Boolean? = null,
  @JsonProperty("creditor_name_required") val creditorNameRequired: Boolean? = null,
  @JsonProperty("creditor_postal_address_required") val creditorPostalAddressRequired: Boolean? = null,
  @JsonProperty("remittance_information_required") val remittanceInformationRequired: Boolean? = null,
  @JsonProperty("remittance_information_lines") val remittanceInformationLines: List<RemittanceInformationLineInfo>? = null,
  @JsonProperty("debtor_currency_required") val debtorCurrencyRequired: Boolean? = null,
  @JsonProperty("debtor_contact_email_required") val debtorContactEmailRequired: Boolean? = null,
  @JsonProperty("debtor_contact_phone_required") val debtorContactPhoneRequired: Boolean? = null,
  @JsonProperty("creditor_agent_bic_fi_required") val creditorAgentBicFiRequired: Boolean? = null,
  @JsonProperty("creditor_agent_clearing_system_member_id_required") val creditorAgentClearingSystemMemberIdRequired: Boolean? = null,
  @JsonProperty("allowed_auth_methods") val allowedAuthMethods: List<String>? = null,
  @JsonProperty("regulatory_reporting_codes") val regulatoryReportingCodes: List<RegulatoryReportingCode>? = null,
  @JsonProperty("regulatory_reporting_code_required") val regulatoryReportingCodeRequired: Boolean? = null,
  @JsonProperty("reference_number_supported") val referenceNumberSupported: Boolean? = null,
  @JsonProperty("reference_number_schemas") val referenceNumberSchemas: List<ReferenceNumberScheme>? = null,
  @JsonProperty("requested_execution_date_supported") val requestedExecutionDateSupported: Boolean? = null,
  @JsonProperty("requested_execution_date_max_period") val requestedExecutionDateMaxPeriod: Int? = null,
  @JsonProperty("remittance_reference_supported") val remittanceReferenceSupported: Boolean? = null,
  @JsonProperty("deferred_submission_supported") val deferredSubmissionSupported: Boolean? = null,
  @JsonProperty("final_successful_statuses") val finalSuccessfulStatuses: List<PaymentStatus>? = null,
  @JsonProperty("psu_type") val psuType: PSUType? = null,
  @JsonProperty("charge_bearer_values") val chargeBearerValues: List<ChargeBearerCode>? = null
)

data class SandboxInfo(
  val users: List<SandboxUser>? = null
)

data class SandboxUser(
  val username: String? = null,
  val password: String? = null,
  val otp: String? = null
)

enum class SchemeName {
  ARNU, BANK, BBAN, BGNR, CCPT, CHID, COID, CPAN, CUSI, CUST, DRLC, DUNS, EMPL, GS1G, IBAN, MIBN, NIDN, OAUT, OTHC, OTHI, PGNR, SOSE
}

enum class Service {
  AIS, PIS
}

enum class ServiceLevelCode {
  BKTR, G001, G002, G003, G004, NUGP, NURG, PRPTEBA, SDVA, SEPA, SVDE
}

data class SessionAccount(
  val uid: UUID,
  @JsonProperty("identification_hash") val identificationHash: String,
  @JsonProperty("identification_hashes") val identificationHashes: List<String>
)

enum class SessionStatus {
  AUTHORIZED, CANCELLED, CLOSED, EXPIRED, INVALID, PENDING_AUTHORIZATION, RETURNED_FROM_BANK, REVOKED
}

data class StartAuthorizationRequest(
  val access: Access,
  val aspsp: ASPSP,
  val state: String,
  @JsonProperty("redirect_url") val redirectUrl: URI,
  @JsonProperty("psu_type") val psuType: PSUType? = null,
  @JsonProperty("auth_method") val authMethod: String? = null,
  val credentials: Any? = null,
  @JsonProperty("psu_id") val psuId: String? = null
)

data class StartAuthorizationResponse(
  val url: URI,
  @JsonProperty("authorization_id") val authorizationId: UUID,
  @JsonProperty("psu_id_hash") val psuIdHash: String
)

data class StatusReasonInformation(
  @JsonProperty("status_reason_code") val statusReasonCode: String,
  @JsonProperty("status_reason_description") val statusReasonDescription: String
)

data class SubmitPaymentResponse(
  @JsonProperty("payment_id") val paymentId: UUID,
  val status: PaymentStatus,
  @JsonProperty("final_status") val finalStatus: Boolean,
  @JsonProperty("status_reason_information") val statusReasonInformation: StatusReasonInformation? = null
)

data class SuccessResponse(
  val message: String? = null
)

data class Transaction(
  @JsonProperty("entry_reference") val entryReference: String? = null,
  @JsonProperty("merchant_category_code") val merchantCategoryCode: String? = null,
  @JsonProperty("transaction_amount") val transactionAmount: AmountType,
  val creditor: PartyIdentification? = null,
  @JsonProperty("creditor_account") val creditorAccount: AccountIdentification? = null,
  @JsonProperty("creditor_agent") val creditorAgent: FinancialInstitutionIdentification? = null,
  val debtor: PartyIdentification? = null,
  @JsonProperty("debtor_account") val debtorAccount: AccountIdentification? = null,
  @JsonProperty("debtor_agent") val debtorAgent: FinancialInstitutionIdentification? = null,
  @JsonProperty("bank_transaction_code") val bankTransactionCode: BankTransactionCode? = null,
  @JsonProperty("credit_debit_indicator") val creditDebitIndicator: CreditDebitIndicator,
  val status: TransactionStatus,
  @JsonProperty("booking_date") val bookingDate: LocalDate? = null,
  @JsonProperty("value_date") val valueDate: LocalDate? = null,
  @JsonProperty("transaction_date") val transactionDate: LocalDate? = null,
  @JsonProperty("balance_after_transaction") val balanceAfterTransaction: AmountType? = null,
  @JsonProperty("reference_number") val referenceNumber: String? = null,
  @JsonProperty("reference_number_schema") val referenceNumberSchema: ReferenceNumberScheme? = null,
  @JsonProperty("remittance_information") val remittanceInformation: List<String>? = null,
  @JsonProperty("debtor_account_additional_identification") val debtorAccountAdditionalIdentification: List<GenericIdentification>? = null,
  @JsonProperty("creditor_account_additional_identification") val creditorAccountAdditionalIdentification: List<GenericIdentification>? = null,
  @JsonProperty("exchange_rate") val exchangeRate: ExchangeRate? = null,
  val note: String? = null,
  @JsonProperty("transaction_id") val transactionId: String? = null
)

enum class TransactionStatus {
  BOOK, CNCL, HOLD, OTHR, PDNG, RJCT, SCHD
}

enum class TransactionsFetchStrategy {
  @JsonProperty("default") DEFAULT,
  @JsonProperty("longest") LONGEST
}

enum class Usage {
  ORGA, PRIV
}

enum class CountryCode {
  AT, BE, BG, CY, CZ, DE, DK, EE, EL, ES,
  FI, FR, HR, HU, IE, IT, LT, LU, LV, MT,
  NL, PL, PT, RO, SE, SI, SK
}
