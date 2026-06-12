package db

import enablebanking.AmountType
import enablebanking.CreditDebitIndicator.CRDT
import enablebanking.Transaction
import enablebanking.TransactionStatus.BOOK
import klite.Email
import users.User
import java.util.UUID.randomUUID

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User(Email("user@user.com"), randomUUID(), Id(2))
  val transaction = Transaction(transactionAmount = AmountType("EUR", "1"), creditDebitIndicator = CRDT, status = BOOK)

}
