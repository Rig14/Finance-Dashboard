package users

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.TestData.user
import klite.base64Decode
import org.junit.jupiter.api.Test

class JwtTest {
  @Test
  fun `creates and validates App token`() {
    val token = Jwt.create(Payload(userId = user.id))

    expect(Jwt.validate(token)).toEqual(true)
    expect(token.split(".").size).toEqual(3)
    expect(getDecodedPayload(token).contains("enablebanking")).toEqual(false)
  }

  @Test
  fun `creates and validates EnableBanking token`() {
    val token = Jwt.create(Payload("enablebanking.com", "api.enablebanking.com"))

    expect(Jwt.validate(token)).toEqual(true)
    expect(token.split(".").size).toEqual(3)
    expect(getDecodedPayload(token).contains("enablebanking")).toEqual(true)
  }

  @Test
  fun `rejects tampered and malformed tokens`() {
    val validToken = Jwt.create(Payload(userId = user.id))

    expect(Jwt.validate(validToken.dropLast(2) + "ab")).toEqual(false)
    expect(Jwt.validate("invalid.token")).toEqual(false)
    expect(Jwt.validate("invalid.token.structure.extra")).toEqual(false)
    expect(Jwt.validate("")).toEqual(false)
  }

  private fun getDecodedPayload(jwt: String) = String(jwt.split(".")[1].base64Decode())
}
