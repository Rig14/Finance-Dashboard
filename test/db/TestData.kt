package db

import enablebanking.AmountType
import enablebanking.CreditDebitIndicator.CRDT
import enablebanking.EnableBankingTransaction
import enablebanking.TransactionStatus.BOOK
import klite.Decimal
import klite.Email
import transactions.SpendCategory
import transactions.Transaction
import users.User
import java.time.LocalDate.now
import java.util.UUID.randomUUID

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User(Email("user@user.com"), randomUUID(), Id(2))
  val enableBankingTransaction = EnableBankingTransaction(transactionAmount = AmountType("EUR", "1"), creditDebitIndicator = CRDT, status = BOOK, entryReference = "20261012293-200")
  val transaction = Transaction(
    amount = Decimal.ONE,
    creditor = "Rimi Eesti OÜ",
    creditDebitIndicator = CRDT,
    date = now().minusWeeks(1),
    userId = user.id,
    categoryCode = SpendCategory.fromMcc("0012"),
    accountId = randomUUID(),
    note = "Transaction 102x92md, some other text",
    entryReference = enableBankingTransaction.entryReference!!
  )
}
