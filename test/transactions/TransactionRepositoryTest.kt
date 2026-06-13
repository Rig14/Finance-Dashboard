package transactions

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.DBTest
import db.TestData.transaction
import db.TestData.user
import klite.Password
import klite.crypto.KeyGenerator
import org.junit.jupiter.api.Test
import users.UserRepository

class TransactionRepositoryTest: DBTest() {
  val repository = TransactionsRepository(db)
  val userRepository = UserRepository(db, KeyGenerator())

  @Test fun `save and load`() {
    userRepository.create(user, Password("abc"))
    repository.save(transaction)
    expect(repository.get(transaction.id)).toEqual(transaction)
  }
}
