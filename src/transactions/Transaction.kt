package transactions

import db.Entity
import db.Id
import enablebanking.CreditDebitIndicator
import klite.Decimal
import klite.StringValue
import users.User
import java.time.LocalDate

class ISO20022(code: String): StringValue(code)

data class Transaction (
  val amount: Decimal,
  val creditor: String,
  val creditDebitIndicator: CreditDebitIndicator,
  val date: LocalDate,
  val userId: Id<User>,
  val categoryCode: ISO20022?,
  val note: String?,
  override val id: Id<Transaction> = Id()
): Entity<Transaction>
