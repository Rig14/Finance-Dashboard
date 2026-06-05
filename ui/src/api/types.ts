export type Id<T extends Entity<T>> = string & {_of?: T}
export type Entity<T extends Entity<T>> = {id: Id<T>}

// class enablebanking.Access
export interface Access {valid_until: Instant}
// class enablebanking.Aspsp
export interface Aspsp {country: string; name: string}
// class enablebanking.AuthorizationRequest
export interface AuthorizationRequest {access: Access; aspsp: Aspsp; psu_type: string; redirect_url: URI; state: string}
// class enablebanking.AuthorizationResponse
export interface AuthorizationResponse {authorization_id: string; psu_id_hash: string; url: URI}
// class users.Role
export enum Role {ADMIN = 'ADMIN', USER = 'USER'}
// class users.User
export interface User {firstName: string; id: Id<User>; lastName: string; role: Role}

// java.time.Instant
export type Instant = `${number}-${number}-${number}T${number}:${number}:${number}Z`
// java.net.URI
export type URI = `${string}://${string}`
