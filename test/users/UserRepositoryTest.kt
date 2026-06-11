package users

import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.DBTest
import db.TestData.user
import klite.Password
import klite.crypto.KeyGenerator
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class UserRepositoryTest: DBTest() {
  val repository = UserRepository(db, KeyGenerator())

  @Test fun `create & get by credentials`() {
    repository.create(user, Password("password"))
    expect(repository.get(user.id)).toEqual(user)
    expect(repository.list()).toContain(user)
    expect(repository.byCredentials(user.id, Password("password"))).toEqual(user)
    expect(repository.byCredentials(user.id, Password("wrong"))).toEqual(null)
  }

  @Test fun `add session to user`() {
    val sessionId = randomUUID()
    val testUser = user.copy(sessionId = null)
    repository.create(testUser, Password("password"))
    repository.update(testUser.copy(sessionId = sessionId))

    val res = repository.byCredentials(testUser.id, Password("password"))!!
    expect(res).toEqual(testUser.copy(sessionId = sessionId))
    expect(res.sessionId).toEqual(sessionId)
  }
}
