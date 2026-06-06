export type Id<T extends Entity<T>> = string & {_of?: T}
export type Entity<T extends Entity<T>> = {id: Id<T>}

// class enablebanking.Access
export interface Access {valid_until: Instant}
// class enablebanking.Account
export interface Account {account_id: AccountId; account_servicer: AccountServicer; all_account_ids: Array<AllAccountId>; cash_account_type: string; credit_limit: CreditLimit; currency: string; details: string; identification_hash: string; identification_hashes: Array<string>; legal_age: boolean; name: string; postal_address: PostalAddress; product: string; psu_status: string; uid: string; usage: string}
// class enablebanking.AccountId
export interface AccountId {iban: string}
// class enablebanking.AccountServicer
export interface AccountServicer {bic_fi: string; clearing_system_member_id: ClearingSystemMemberId; name: string}
// class enablebanking.AllAccountId
export interface AllAccountId {identification: string; scheme_name: string}
// class enablebanking.Aspsp
export interface Aspsp {country: string; name: string}
// class enablebanking.AuthorizationRequest
export interface AuthorizationRequest {access: Access; aspsp: Aspsp; psu_type: string; redirect_url: URI; state: string}
// class enablebanking.AuthorizationResponse
export interface AuthorizationResponse {authorization_id: string; psu_id_hash: string; url: URI}
// class enablebanking.ClearingSystemMemberId
export interface ClearingSystemMemberId {clearing_system_id: string; member_id: number}
// class enablebanking.CreateSessionRequest
export interface CreateSessionRequest {code: string}
// class enablebanking.CreateSessionResponse
export interface CreateSessionResponse {access: Access; accounts: Array<Account>; aspsp: Aspsp; psu_type: string; session_id: string}
// class enablebanking.CreditLimit
export interface CreditLimit {amount: string; currency: string}
// class enablebanking.PostalAddress
export interface PostalAddress {address_line: Array<string>; address_type: string; building_number: string; country: string; country_sub_division: string; department: string; post_code: string; street_name: string; sub_department: string; town_name: string}
// class users.Role
export enum Role {ADMIN = 'ADMIN', USER = 'USER'}
// class users.User
export interface User {firstName: string; id: Id<User>; lastName: string; role: Role}

// java.time.Instant
export type Instant = `${number}-${number}-${number}T${number}:${number}:${number}Z`
// java.net.URI
export type URI = `${string}://${string}`
