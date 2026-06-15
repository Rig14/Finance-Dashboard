package transactions

import db.Entity
import db.Id
import enablebanking.CreditDebitIndicator
import enablebanking.ISO20022
import klite.Decimal
import users.User
import java.time.LocalDate
import java.util.*

data class Transaction (
  val amount: Decimal,
  val creditDebitIndicator: CreditDebitIndicator,
  val userId: Id<User>,
  val accountId: UUID,
  val hashCode: Int,
  val date: LocalDate?,
  val creditor: String?,
  val categoryCode: ISO20022?,
  val note: String?,
  override val id: Id<Transaction> = Id()
): Entity<Transaction>
