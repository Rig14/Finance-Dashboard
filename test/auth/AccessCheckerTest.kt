package auth

import db.BaseMocks
import db.TestData.user
import io.mockk.every
import klite.ForbiddenException
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AccessCheckerTest: BaseMocks() {
  val checker = create<AccessChecker>()

  @Test fun `allows public access`() = runTest {
    every { exchange.session["userId"] } returns null
    every { exchange.route.annotations } returns listOf(Public())
    checker.before(exchange)
  }

  @Test fun `forbids unauthorized access`() = runTest {
    every { exchange.session["userId"] } returns null
    assertThrows<ForbiddenException> { checker.before(exchange) }
  }


  @Test fun `requires @Access annotation`() = runTest {
    every { exchange.session["userId"] } returns user.id.toString()
    assertThrows<IllegalStateException> { checker.before(exchange) }
  }
}
