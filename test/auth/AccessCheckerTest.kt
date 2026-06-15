package auth

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.BaseMocks
import io.mockk.every
import klite.ForbiddenException
import klite.NotFoundRoute
import klite.RequestMethod.GET
import klite.Route
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import users.Jwt
import users.Payload
import db.TestData.user as testUser

class AccessCheckerTest: BaseMocks() {
  val checker = AccessChecker(userRepository)

  @BeforeEach
  fun setUp() {
    every { exchange.cookie(any<String>()) } returns null
  }

  @Test fun `throws ForbiddenException for access routes without user`() = runTest {
    every { exchange.route } returns Route(GET, "/".toRegex(), annotations = listOf(Access())) {}
    try {
      checker.before(exchange)
      throw AssertionError("Expected ForbiddenException")
    } catch (_: ForbiddenException) { }
  }

  @Test fun `throws ForbiddenException for unannotated routes without user`() = runTest {
    every { exchange.route } returns Route(GET, "/".toRegex()) {}
    try {
      checker.before(exchange)
      throw AssertionError("Expected ForbiddenException")
    } catch (_: ForbiddenException) { }
  }

  @Test fun `throws error when Access annotation is missing for non-public routes with user`() = runTest {
    every { exchange.route } returns Route(GET, "/".toRegex()) {}
    every { exchange.cookie("jwt") } returns Jwt.create(Payload(userId = testUser.id))
    try {
      checker.before(exchange)
      throw AssertionError("Expected IllegalStateException")
    } catch (e: IllegalStateException) {
      expect(e.message).toEqual("@Access annotation is required for non-@Public routes")
    }
  }

  @Test fun `allows access to NotFoundRoute without user`() = runTest {
    every { exchange.route } returns NotFoundRoute("/notfound") {}
    checker.before(exchange)
  }
}
