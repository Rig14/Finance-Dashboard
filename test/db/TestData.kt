package db

import enablebanking.AmountType
import enablebanking.CreditDebitIndicator.CRDT
import enablebanking.EnableBankingTransaction
import enablebanking.TransactionStatus.BOOK
import klite.Decimal
import klite.Email
import transactions.ISO20022
import transactions.Transaction
import users.User
import java.time.LocalDate.now
import java.util.UUID.randomUUID

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User(Email("user@user.com"), randomUUID(), Id(2))
  val enableBankingTransaction = EnableBankingTransaction(transactionAmount = AmountType("EUR", "1"), creditDebitIndicator = CRDT, status = BOOK)
  val transaction = Transaction(
    amount = Decimal.ONE,
    creditor = "Rimi Eesti OÜ",
    creditDebitIndicator = CRDT,
    date = now().minusWeeks(1),
    userId = user.id,
    categoryCode = ISO20022("0012"),
    note = "Transaction 102x92md, some other text",
  )
}
