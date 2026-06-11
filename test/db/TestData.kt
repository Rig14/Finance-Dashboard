package db

import klite.Email
import users.User
import java.util.*

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User(Email("user@user.com"), UUID.randomUUID(), Id(2))
}
