export type Id<T extends Entity<T>> = string & {_of?: T}
export type Entity<T extends Entity<T>> = {id: Id<T>}

// class enablebanking.ASPSP
export interface ASPSP {country: string; name: string}
// class enablebanking.ASPSPData
export interface ASPSPData {auth_methods: Array<AuthMethod>; beta: boolean; bic?: string; country: string; group?: ASPSPGroup; logo: URI; maximum_consent_validity: number; name: string; payments?: Array<ResponsePaymentType>; psu_types: Array<PSUType>; required_psu_headers?: Array<string>; sandbox?: SandboxInfo}
// class enablebanking.ASPSPGroup
export interface ASPSPGroup {logo: URI; name: string}
// class enablebanking.Access
export interface Access {accounts?: Array<AccountIdentification>; balances?: boolean; transactions?: boolean; valid_until: Instant}
// class enablebanking.AccountIdentification
export interface AccountIdentification {iban?: string; other?: GenericIdentification}
// class enablebanking.AccountResource
export interface AccountResource {account_id?: AccountIdentification; account_servicer?: FinancialInstitutionIdentification; all_account_ids?: Array<GenericIdentification>; cash_account_type?: CashAccountType; credit_limit?: AmountType; currency: string; details?: string; identification_hash: string; identification_hashes: Array<string>; legal_age?: boolean; name?: string; postal_address?: PostalAddress; product?: string; psu_status?: string; uid?: string; usage?: Usage}
// class enablebanking.AddressType
export enum AddressType {BUSINESS = 'BUSINESS', CORRESPONDENCE = 'CORRESPONDENCE', DELIVERY_TO = 'DELIVERY_TO', MAIL_TO = 'MAIL_TO', PO_BOX = 'PO_BOX', POSTAL = 'POSTAL', RESIDENTIAL = 'RESIDENTIAL', STATEMENT = 'STATEMENT'}
// class enablebanking.AmountType
export interface AmountType {amount: string; currency: string}
// class enablebanking.AuthMethod
export interface AuthMethod {approach: AuthenticationApproach; credentials?: Array<Credential>; hidden_method: boolean; name?: string; psu_type: PSUType; title?: string}
// class enablebanking.AuthenticationApproach
export enum AuthenticationApproach {DECOUPLED = 'DECOUPLED', EMBEDDED = 'EMBEDDED', REDIRECT = 'REDIRECT'}
// class enablebanking.AuthorizeSessionRequest
export interface AuthorizeSessionRequest {code: string}
// class enablebanking.AuthorizeSessionResponse
export interface AuthorizeSessionResponse {access: Access; accounts: Array<AccountResource>; aspsp: ASPSP; psu_type: PSUType; session_id: string}
// class enablebanking.BalanceResource
export interface BalanceResource {balance_amount: AmountType; balance_type: BalanceStatus; last_change_date_time?: Instant; last_committed_transaction?: string; name: string; reference_date?: LocalDate}
// class enablebanking.BalanceStatus
export enum BalanceStatus {CLAV = 'CLAV', CLBD = 'CLBD', FWAV = 'FWAV', INFO = 'INFO', ITAV = 'ITAV', ITBD = 'ITBD', OPAV = 'OPAV', OPBD = 'OPBD', OTHR = 'OTHR', PRCD = 'PRCD', VALU = 'VALU', XPCD = 'XPCD'}
// class enablebanking.BankTransactionCode
export interface BankTransactionCode {code?: string; description?: string; sub_code?: string}
// class enablebanking.Beneficiary
export interface Beneficiary {creditor?: PartyIdentification; creditor_account: GenericIdentification; creditor_agent?: FinancialInstitutionIdentification; creditor_currency?: string}
// class enablebanking.CashAccountType
export enum CashAccountType {CACC = 'CACC', CARD = 'CARD', CASH = 'CASH', LOAN = 'LOAN', OTHR = 'OTHR', SVGS = 'SVGS'}
// class enablebanking.CategoryPurposeCode
export enum CategoryPurposeCode {BONU = 'BONU', CASH = 'CASH', CBLK = 'CBLK', CCRD = 'CCRD', CORT = 'CORT', DCRD = 'DCRD', DIVI = 'DIVI', DVPM = 'DVPM', EPAY = 'EPAY', FCOL = 'FCOL', GOVT = 'GOVT', HEDG = 'HEDG', ICCP = 'ICCP', IDCP = 'IDCP', INTC = 'INTC', INTE = 'INTE', LOAN = 'LOAN', MP2B = 'MP2B', MP2P = 'MP2P', OTHR = 'OTHR', PENS = 'PENS', RPRE = 'RPRE', RRCT = 'RRCT', RVPM = 'RVPM', SALA = 'SALA', SECU = 'SECU', SSBE = 'SSBE', SUPP = 'SUPP', TAXS = 'TAXS', TRAD = 'TRAD', TREA = 'TREA', VATX = 'VATX', WHLD = 'WHLD'}
// class enablebanking.ChargeBearerCode
export enum ChargeBearerCode {CRED = 'CRED', DEBT = 'DEBT', SHAR = 'SHAR', SLEV = 'SLEV'}
// class enablebanking.ClearingSystemMemberIdentification
export interface ClearingSystemMemberIdentification {clearing_system_id?: string; member_id?: string}
// class enablebanking.ContactDetails
export interface ContactDetails {email_address?: string; phone_number?: string}
// class enablebanking.CountryCode
export enum CountryCode {AT = 'AT', BE = 'BE', BG = 'BG', CY = 'CY', CZ = 'CZ', DE = 'DE', DK = 'DK', EE = 'EE', EL = 'EL', ES = 'ES', FI = 'FI', FR = 'FR', HR = 'HR', HU = 'HU', IE = 'IE', IT = 'IT', LT = 'LT', LU = 'LU', LV = 'LV', MT = 'MT', NL = 'NL', PL = 'PL', PT = 'PT', RO = 'RO', SE = 'SE', SI = 'SI', SK = 'SK'}
// class enablebanking.CreatePaymentRequest
export interface CreatePaymentRequest {aspsp: ASPSP; auth_method?: string; credentials?: any; defer_submission?: boolean; language?: string; payment_request: PaymentRequestResource; payment_type: PaymentType; psu_id?: string; psu_type: PSUType; redirect_url: URI; state: string; webhook_url?: URI}
// class enablebanking.CreatePaymentResponse
export interface CreatePaymentResponse {payment_id: string; psu_id_hash: string; status: PaymentStatus; url: URI}
// class enablebanking.Credential
export interface Credential {description?: string; name: string; required: boolean; template?: string; title: string}
// class enablebanking.CreditDebitIndicator
export enum CreditDebitIndicator {CRDT = 'CRDT', DBIT = 'DBIT'}
// class enablebanking.CreditTransferTransaction
export interface CreditTransferTransaction {beneficiary: Beneficiary; end_date?: LocalDate; execution_rule?: ExecutionRule; frequency?: FrequencyCode; instructed_amount: AmountType; payment_id?: PaymentIdentification; reference_number?: string; regulatory_reporting?: Array<RegulatoryReporting>; remittance_information?: Array<string>; requested_execution_date?: LocalDate; ultimate_creditor?: PartyIdentification; ultimate_debtor?: PartyIdentification}
// class enablebanking.CreditTransferTransactionDetails
export interface CreditTransferTransactionDetails {beneficiary: Beneficiary; end_date?: LocalDate; execution_rule?: ExecutionRule; frequency?: FrequencyCode; instructed_amount: AmountType; payment_id?: PaymentIdentification; reference_number?: string; regulatory_reporting?: Array<RegulatoryReporting>; remittance_information?: Array<string>; requested_execution_date?: LocalDate; transaction_id?: string; transaction_status?: PaymentStatus; ultimate_creditor?: PartyIdentification; ultimate_debtor?: PartyIdentification}
// class enablebanking.Environment
export enum Environment {PRODUCTION = 'PRODUCTION', SANDBOX = 'SANDBOX'}
// class enablebanking.ErrorCode
export enum ErrorCode {ACCESS_DENIED = 'ACCESS_DENIED', ACCOUNT_DOES_NOT_EXIST = 'ACCOUNT_DOES_NOT_EXIST', ALREADY_AUTHORIZED = 'ALREADY_AUTHORIZED', ASPSP_ACCOUNT_NOT_ACCESSIBLE = 'ASPSP_ACCOUNT_NOT_ACCESSIBLE', ASPSP_ERROR = 'ASPSP_ERROR', ASPSP_PAYMENT_NOT_ACCESSIBLE = 'ASPSP_PAYMENT_NOT_ACCESSIBLE', ASPSP_PSU_ACTION_REQUIRED = 'ASPSP_PSU_ACTION_REQUIRED', ASPSP_RATE_LIMIT_EXCEEDED = 'ASPSP_RATE_LIMIT_EXCEEDED', ASPSP_TIMEOUT = 'ASPSP_TIMEOUT', AUTHORIZATION_NOT_PROVIDED = 'AUTHORIZATION_NOT_PROVIDED', CLOSED_SESSION = 'CLOSED_SESSION', DATE_FROM_IN_FUTURE = 'DATE_FROM_IN_FUTURE', DATE_TO_WITHOUT_DATE_FROM = 'DATE_TO_WITHOUT_DATE_FROM', EXPIRED_AUTHORIZATION_CODE = 'EXPIRED_AUTHORIZATION_CODE', EXPIRED_SESSION = 'EXPIRED_SESSION', INVALID_ACCOUNT_ID = 'INVALID_ACCOUNT_ID', INVALID_HOST = 'INVALID_HOST', INVALID_PAYMENT = 'INVALID_PAYMENT', NO_ACCOUNTS_ADDED = 'NO_ACCOUNTS_ADDED', PAYMENT_LIMIT_EXCEEDED = 'PAYMENT_LIMIT_EXCEEDED', PAYMENT_NOT_AUTHORIZED = 'PAYMENT_NOT_AUTHORIZED', PAYMENT_NOT_FINALIZED = 'PAYMENT_NOT_FINALIZED', PAYMENT_NOT_FOUND = 'PAYMENT_NOT_FOUND', PAYMENT_SUBMISSION_NOT_DEFERRED = 'PAYMENT_SUBMISSION_NOT_DEFERRED', PAYMENT_SUBMISSION_NOT_SUPPORTED = 'PAYMENT_SUBMISSION_NOT_SUPPORTED', PSU_HEADER_INVALID = 'PSU_HEADER_INVALID', PSU_HEADER_NOT_PROVIDED = 'PSU_HEADER_NOT_PROVIDED', REDIRECT_URI_NOT_ALLOWED = 'REDIRECT_URI_NOT_ALLOWED', REVOKED_SESSION = 'REVOKED_SESSION', SESSION_DOES_NOT_EXIST = 'SESSION_DOES_NOT_EXIST', TRANSACTION_DOES_NOT_EXIST = 'TRANSACTION_DOES_NOT_EXIST', UNAUTHORIZED_ACCESS = 'UNAUTHORIZED_ACCESS', UNAUTHORIZED_IP = 'UNAUTHORIZED_IP', UNTRUSTED_PAYMENT_PARTY = 'UNTRUSTED_PAYMENT_PARTY', WEBHOOK_URI_NOT_ALLOWED = 'WEBHOOK_URI_NOT_ALLOWED', WRONG_ASPSP_PROVIDED = 'WRONG_ASPSP_PROVIDED', WRONG_AUTHORIZATION_CODE = 'WRONG_AUTHORIZATION_CODE', WRONG_CONTINUATION_KEY = 'WRONG_CONTINUATION_KEY', WRONG_CREDENTIALS_PROVIDED = 'WRONG_CREDENTIALS_PROVIDED', WRONG_DATE_INTERVAL = 'WRONG_DATE_INTERVAL', WRONG_REQUEST_PARAMETERS = 'WRONG_REQUEST_PARAMETERS', WRONG_SESSION_STATUS = 'WRONG_SESSION_STATUS', WRONG_TRANSACTIONS_PERIOD = 'WRONG_TRANSACTIONS_PERIOD'}
// class enablebanking.ErrorResponse
export interface ErrorResponse {code?: number; detail?: any; error?: ErrorCode; message: string}
// class enablebanking.ExchangeRate
export interface ExchangeRate {contract_identification?: string; exchange_rate?: string; instructed_amount?: AmountType; rate_type?: RateType; unit_currency?: string}
// class enablebanking.ExecutionRule
export enum ExecutionRule {FWNG = 'FWNG', PREC = 'PREC'}
// class enablebanking.FinancialInstitutionIdentification
export interface FinancialInstitutionIdentification {bic_fi?: string; clearing_system_member_id?: ClearingSystemMemberIdentification; name?: string}
// class enablebanking.FrequencyCode
export enum FrequencyCode {DAIL = 'DAIL', MNTH = 'MNTH', QUTR = 'QUTR', SEMI = 'SEMI', TOMN = 'TOMN', TOWK = 'TOWK', WEEK = 'WEEK', YEAR = 'YEAR'}
// class enablebanking.GenericIdentification
export interface GenericIdentification {identification: string; issuer?: string; scheme_name: SchemeName}
// class enablebanking.GetApplicationResponse
export interface GetApplicationResponse {active: boolean; countries: Array<string>; description?: string; environment: Environment; kid: string; name: string; redirect_urls: Array<string>; services: Array<Service>}
// class enablebanking.GetAspspsResponse
export interface GetAspspsResponse {aspsps: Array<ASPSPData>}
// class enablebanking.GetPaymentResponse
export interface GetPaymentResponse {aspsp: ASPSP; final_status: boolean; payment_details: PaymentRequestResourceDetails; payment_id: string; payment_type: PaymentType; psu_id_hash: string; status: PaymentStatus; status_reason_information?: StatusReasonInformation}
// class enablebanking.GetPaymentTransactionResponse
export interface GetPaymentTransactionResponse {payment_id: string; transaction_details: CreditTransferTransactionDetails}
// class enablebanking.GetSessionResponse
export interface GetSessionResponse {access: Access; accounts: Array<string>; accounts_data: Array<SessionAccount>; aspsp: ASPSP; authorized?: Instant; closed?: Instant; created: Instant; psu_id_hash: string; psu_type: PSUType; status: SessionStatus}
// class enablebanking.HalBalances
export interface HalBalances {balances: Array<BalanceResource>}
// class enablebanking.HalTransactions
export interface HalTransactions {continuation_key?: string; transactions: Array<Transaction>}
// class enablebanking.PSUType
export enum PSUType {BUSINESS = 'BUSINESS', PERSONAL = 'PERSONAL'}
// class enablebanking.PartyIdentification
export interface PartyIdentification {contact_details?: ContactDetails; name?: string; organisation_id?: GenericIdentification; postal_address?: PostalAddress; private_id?: GenericIdentification}
// class enablebanking.PaymentIdentification
export interface PaymentIdentification {end_to_end_id?: string; instruction_id?: string}
// class enablebanking.PaymentRequestResource
export interface PaymentRequestResource {charge_bearer?: ChargeBearerCode; credit_transfer_transaction: Array<CreditTransferTransaction>; debtor?: PartyIdentification; debtor_account?: GenericIdentification; debtor_agent?: FinancialInstitutionIdentification; debtor_currency?: string; payment_information_id?: string; payment_type_information?: PaymentTypeInformation; purpose?: PurposeCode}
// class enablebanking.PaymentRequestResourceDetails
export interface PaymentRequestResourceDetails {charge_bearer?: ChargeBearerCode; credit_transfer_transaction?: Array<CreditTransferTransactionDetails>; debtor?: PartyIdentification; debtor_account?: GenericIdentification; debtor_agent?: FinancialInstitutionIdentification; debtor_currency?: string; payment_information_id?: string; payment_type_information?: PaymentTypeInformation; purpose?: PurposeCode}
// class enablebanking.PaymentStatus
export enum PaymentStatus {ACCC = 'ACCC', ACCP = 'ACCP', ACCR = 'ACCR', ACPT = 'ACPT', ACSC = 'ACSC', ACSP = 'ACSP', ACTC = 'ACTC', ACWC = 'ACWC', ACWP = 'ACWP', CNCL = 'CNCL', NULL = 'NULL', PACR = 'PACR', PART = 'PART', PDCR = 'PDCR', PDNG = 'PDNG', RCVD = 'RCVD', RJCR = 'RJCR', RJCT = 'RJCT'}
// class enablebanking.PaymentType
export enum PaymentType {BULK_DOMESTIC = 'BULK_DOMESTIC', BULK_DOMESTIC_SE_GIRO = 'BULK_DOMESTIC_SE_GIRO', BULK_SEPA = 'BULK_SEPA', CROSSBORDER = 'CROSSBORDER', DOMESTIC = 'DOMESTIC', DOMESTIC_SE_GIRO = 'DOMESTIC_SE_GIRO', INST_SEPA = 'INST_SEPA', INTERNAL = 'INTERNAL', SEPA = 'SEPA'}
// class enablebanking.PaymentTypeInformation
export interface PaymentTypeInformation {category_purpose?: CategoryPurposeCode; instruction_priority?: PriorityCode; local_instrument?: string; service_level?: ServiceLevelCode}
// class enablebanking.PostalAddress
export interface PostalAddress {address_line?: Array<string>; address_type?: AddressType; building_number?: string; country?: string; country_sub_division?: string; department?: string; post_code?: string; street_name?: string; sub_department?: string; town_name?: string}
// class enablebanking.PriorityCode
export enum PriorityCode {EXPR = 'EXPR', HIGH = 'HIGH', NORM = 'NORM'}
// class enablebanking.PurposeCode
export enum PurposeCode {ACCT = 'ACCT', CASH = 'CASH', COMC = 'COMC', CPKC = 'CPKC', TRPT = 'TRPT'}
// class enablebanking.RateType
export enum RateType {AGRD = 'AGRD', SALE = 'SALE', SPOT = 'SPOT'}
// class enablebanking.ReferenceNumberScheme
export enum ReferenceNumberScheme {BERF = 'BERF', FIRF = 'FIRF', INTL = 'INTL', NORF = 'NORF', SDDM = 'SDDM', SEBG = 'SEBG'}
// class enablebanking.RegulatoryAuthority
export interface RegulatoryAuthority {country: string; name: string}
// class enablebanking.RegulatoryReporting
export interface RegulatoryReporting {authority?: RegulatoryAuthority; details: RegulatoryReportingDetails}
// class enablebanking.RegulatoryReportingCode
export interface RegulatoryReportingCode {description: string; value: string}
// class enablebanking.RegulatoryReportingDetails
export interface RegulatoryReportingDetails {amount?: AmountType; code?: string; information: string}
// class enablebanking.RemittanceInformationLineInfo
export interface RemittanceInformationLineInfo {max_length?: number; min_length?: number; pattern?: string}
// class enablebanking.ResponsePaymentType
export interface ResponsePaymentType {allowed_auth_methods?: Array<string>; charge_bearer_values?: Array<ChargeBearerCode>; creditor_account_schemas?: Array<SchemeName>; creditor_agent_bic_fi_required?: boolean; creditor_agent_clearing_system_member_id_required?: boolean; creditor_country_required?: boolean; creditor_name_required?: boolean; creditor_postal_address_required?: boolean; currencies?: Array<string>; debtor_account_required?: boolean; debtor_account_schemas?: Array<SchemeName>; debtor_contact_email_required?: boolean; debtor_contact_phone_required?: boolean; debtor_currency_required?: boolean; deferred_submission_supported?: boolean; final_successful_statuses?: Array<PaymentStatus>; max_transactions?: number; payment_type?: PaymentType; priority_codes?: Array<PriorityCode>; psu_type?: PSUType; reference_number_schemas?: Array<ReferenceNumberScheme>; reference_number_supported?: boolean; regulatory_reporting_code_required?: boolean; regulatory_reporting_codes?: Array<RegulatoryReportingCode>; remittance_information_lines?: Array<RemittanceInformationLineInfo>; remittance_information_required?: boolean; remittance_reference_supported?: boolean; requested_execution_date_max_period?: number; requested_execution_date_supported?: boolean}
// class enablebanking.SandboxInfo
export interface SandboxInfo {users?: Array<SandboxUser>}
// class enablebanking.SandboxUser
export interface SandboxUser {otp?: string; password?: string; username?: string}
// class enablebanking.SchemeName
export enum SchemeName {ARNU = 'ARNU', BANK = 'BANK', BBAN = 'BBAN', BGNR = 'BGNR', CCPT = 'CCPT', CHID = 'CHID', COID = 'COID', CPAN = 'CPAN', CUSI = 'CUSI', CUST = 'CUST', DRLC = 'DRLC', DUNS = 'DUNS', EMPL = 'EMPL', GS1G = 'GS1G', IBAN = 'IBAN', MIBN = 'MIBN', NIDN = 'NIDN', OAUT = 'OAUT', OTHC = 'OTHC', OTHI = 'OTHI', PGNR = 'PGNR', SOSE = 'SOSE'}
// class enablebanking.Service
export enum Service {AIS = 'AIS', PIS = 'PIS'}
// class enablebanking.ServiceLevelCode
export enum ServiceLevelCode {BKTR = 'BKTR', G001 = 'G001', G002 = 'G002', G003 = 'G003', G004 = 'G004', NUGP = 'NUGP', NURG = 'NURG', PRPTEBA = 'PRPTEBA', SDVA = 'SDVA', SEPA = 'SEPA', SVDE = 'SVDE'}
// class enablebanking.SessionAccount
export interface SessionAccount {identification_hash: string; identification_hashes: Array<string>; uid: string}
// class enablebanking.SessionStatus
export enum SessionStatus {AUTHORIZED = 'AUTHORIZED', CANCELLED = 'CANCELLED', CLOSED = 'CLOSED', EXPIRED = 'EXPIRED', INVALID = 'INVALID', PENDING_AUTHORIZATION = 'PENDING_AUTHORIZATION', RETURNED_FROM_BANK = 'RETURNED_FROM_BANK', REVOKED = 'REVOKED'}
// class enablebanking.StartAuthorizationRequest
export interface StartAuthorizationRequest {access: Access; aspsp: ASPSP; auth_method?: string; credentials?: any; psu_id?: string; psu_type?: PSUType; redirect_url: URI; state: string}
// class enablebanking.StartAuthorizationResponse
export interface StartAuthorizationResponse {authorization_id: string; psu_id_hash: string; url: URI}
// class enablebanking.StatusReasonInformation
export interface StatusReasonInformation {status_reason_code: string; status_reason_description: string}
// class enablebanking.SubmitPaymentResponse
export interface SubmitPaymentResponse {final_status: boolean; payment_id: string; status: PaymentStatus; status_reason_information?: StatusReasonInformation}
// class enablebanking.SuccessResponse
export interface SuccessResponse {message?: string}
// class enablebanking.Transaction
export interface Transaction {balance_after_transaction?: AmountType; bank_transaction_code?: BankTransactionCode; booking_date?: LocalDate; credit_debit_indicator: CreditDebitIndicator; creditor?: PartyIdentification; creditor_account?: AccountIdentification; creditor_account_additional_identification?: Array<GenericIdentification>; creditor_agent?: FinancialInstitutionIdentification; debtor?: PartyIdentification; debtor_account?: AccountIdentification; debtor_account_additional_identification?: Array<GenericIdentification>; debtor_agent?: FinancialInstitutionIdentification; entry_reference?: string; exchange_rate?: ExchangeRate; merchant_category_code?: string; note?: string; reference_number?: string; reference_number_schema?: ReferenceNumberScheme; remittance_information?: Array<string>; status: TransactionStatus; transaction_amount: AmountType; transaction_date?: LocalDate; transaction_id?: string; value_date?: LocalDate}
// class enablebanking.TransactionStatus
export enum TransactionStatus {BOOK = 'BOOK', CNCL = 'CNCL', HOLD = 'HOLD', OTHR = 'OTHR', PDNG = 'PDNG', RJCT = 'RJCT', SCHD = 'SCHD'}
// class enablebanking.TransactionsFetchStrategy
export enum TransactionsFetchStrategy {DEFAULT = 'DEFAULT', LONGEST = 'LONGEST'}
// class enablebanking.Usage
export enum Usage {ORGA = 'ORGA', PRIV = 'PRIV'}
// class users.Payload
export interface Payload {aud?: string; exp: number; iat: number; iss?: string; userId?: Id<User>}
// class users.User
export interface User {email: Email; id: Id<User>; sessionId?: string}

// java.time.LocalDate
export type LocalDate = `${number}-${number}-${number}`
// java.time.Instant
export type Instant = `${number}-${number}-${number}T${number}:${number}:${number}Z`
// java.net.URI
export type URI = `${string}://${string}`
// klite.Email
export type Email = `${string}@${string}`
