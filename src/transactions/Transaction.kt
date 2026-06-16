package transactions

import db.Entity
import db.Id
import enablebanking.CreditDebitIndicator
import klite.Decimal
import users.User
import java.time.LocalDate
import java.util.*

data class Transaction (
  val amount: Decimal,
  val creditDebitIndicator: CreditDebitIndicator,
  val userId: Id<User>,
  val accountId: UUID,
  val entryReference: String,
  val date: LocalDate?,
  val creditor: String?,
  val categoryCode: SpendCategory,
  val note: String?,
  override val id: Id<Transaction> = Id()
): Entity<Transaction>

enum class SpendCategory {
  HOUSING_AND_UTILITIES,
  GROCERIES,
  GAS_AND_FUEL,
  TRANSIT_AND_COMMUTING,
  AUTO_MAINTENANCE,
  HEALTHCARE,
  RESTAURANTS,
  FAST_FOOD_AND_COFFEE,
  ENTERTAINMENT,
  TRAVEL_AND_VACATION,
  SHOPPING_AND_CLOTHING,
  PERSONAL_CARE,
  SUBSCRIPTIONS_AND_DIGITAL,
  FINANCIAL_AND_FEES,
  OTHER;

  companion object {
    fun fromMcc(mcc: String): SpendCategory {
      val code = mcc.toIntOrNull() ?: return OTHER

      return when (code) {
        4814, 4899, 4900, in 1500..2999 -> HOUSING_AND_UTILITIES
        in 5400..5411, 5499 -> GROCERIES
        5541, 5542 -> GAS_AND_FUEL
        4111, 4121, 4131 -> TRANSIT_AND_COMMUTING
        in 5500..5539, in 7531..7549 -> AUTO_MAINTENANCE
        5812 -> RESTAURANTS
        5814 -> FAST_FOOD_AND_COFFEE
        5811, 5813, in 7800..7999 -> ENTERTAINMENT
        in 3000..3999, in 4000..4799, in 7000..7199 -> TRAVEL_AND_VACATION
        in 4816..4821, 5815 -> SUBSCRIPTIONS_AND_DIGITAL
        in 5000..5399, in 5600..5799, in 5900..5999 -> SHOPPING_AND_CLOTHING
        in 7230..7299 -> PERSONAL_CARE
        in 8000..8099 -> HEALTHCARE
        in 6000..6999, in 9000..9999 -> FINANCIAL_AND_FEES
        else -> OTHER
      }
    }
  }
}
