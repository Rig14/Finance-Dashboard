package users

import db.CrudRepository
import db.Id
import klite.Password
import klite.base64Encode
import klite.crypto.KeyGenerator
import klite.jdbc.exec
import klite.jdbc.insert
import klite.jdbc.select
import klite.mapOfNotNull
import javax.sql.DataSource

class UserRepository(
  db: DataSource,
  private val keyGenerator: KeyGenerator
): CrudRepository<User>(db, "users") {
  fun setAppUser(user: User) { db.exec("call set_app_user(?)", sequenceOf(user.id)) }

  fun create(user: User, secret: Password) {
    db.insert(table, user.persister() + mapOfNotNull("secretHash" to hash(user.id, secret)))
  }

  fun byCredentials(userId: Id<User>, secret: Password) = db.select(table, User::id to userId, "secretHash" to hash(userId, secret)) { mapper() }.firstOrNull()

  internal fun hash(id: Id<User>, secret: Password) = keyGenerator.hash(secret.value, id.toString()).base64Encode()
}
