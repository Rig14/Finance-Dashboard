package users

import db.Entity
import db.Id
import klite.Email
import java.util.*

data class User(
  val email: Email,
  val sessionId: UUID?,
  override val id: Id<User> = Id()
): Entity<User>
