package users

import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.DBTest
import db.TestData.user
import klite.Password
import klite.crypto.KeyGenerator
import org.junit.jupiter.api.Test

class UserRepositoryTest: DBTest() {
  val repository = UserRepository(db, KeyGenerator())

  @Test fun `save & load`() {
    repository.create(user, Password("password"))
    expect(repository.get(user.id)).toEqual(user)
    expect(repository.list()).toContain(user)
    expect(repository.byCredentials(user.id, Password("password"))).toEqual(user)
    expect(repository.byCredentials(user.id, Password("wrong"))).toEqual(null)
  }
}
