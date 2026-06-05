export type Id<T extends Entity<T>> = string & {_of?: T}
export type Entity<T extends Entity<T>> = {id: Id<T>}

// class users.Role
export enum Role {ADMIN = 'ADMIN', USER = 'USER'}
// class users.User
export interface User {firstName: string; id: Id<User>; lastName: string; role: Role}

