package users

import db.Entity
import db.Id
import klite.Email

data class User(
  val email: Email,
  override val id: Id<User> = Id()
): Entity<User>
