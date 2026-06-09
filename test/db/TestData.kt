package db

import klite.Email
import users.User

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User(Email("user@user.com"), Id(2))
}
