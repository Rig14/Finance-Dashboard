package users

import db.Id
import klite.BadRequestException
import klite.base64Decode
import klite.base64UrlDecode
import klite.base64UrlEncode
import klite.json.JsonMapper
import klite.json.parse
import java.io.File
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.interfaces.RSAPrivateCrtKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPublicKeySpec
import java.util.*

data class Payload(
  val iss: String? = null,
  val aud: String? = null,
  val userId: Id<User>? = null,
  val iat: Long = System.currentTimeMillis() / 1000,
  val exp: Long = System.currentTimeMillis() / 1000 + 3600,
)

class Jwt {
  companion object {
    private val privateKeyFile = File("./certs").listFiles().firstOrNull() ?: throw IllegalStateException("could not find ./certs")
    private val publicKey = getPublicKey()
    private val privateKey = getPrivateKey()
    private val jsonMapper = JsonMapper()

    fun create(payload: Payload): String {
      val header = """{"typ":"JWT","alg":"RS256","kid":"${privateKeyFile.nameWithoutExtension}"}"""
      val unsignedToken = "${header.toByteArray().base64UrlEncode()}.${jsonMapper.render(payload).toByteArray().base64UrlEncode()}"

      val signature = Signature.getInstance("SHA256withRSA")
      signature.initSign(privateKey)
      signature.update(unsignedToken.toByteArray())

      return "$unsignedToken.${signature.sign().base64UrlEncode()}"
    }

    fun validate(jwt: String): Boolean {
      val parts = jwt.split(".")
      if (parts.size != 3) return false

      val signature = Signature.getInstance("SHA256withRSA")
      signature.initVerify(publicKey)
      signature.update("${parts[0]}.${parts[1]}".toByteArray())

      return signature.verify(Base64.getUrlDecoder().decode(parts[2]))
    }

    fun parse(jwt: String): Payload {
      val encodedPayload = jwt.split(".").takeIf { it.size >= 2 }?.let { it[1] }
      if (encodedPayload == null) throw BadRequestException("JWT format is not supported")

      val payload = jsonMapper.parse<Payload>(encodedPayload.base64UrlDecode().decodeToString())
      return payload
    }

    private fun getPrivateKey(): PrivateKey {
      val cleanPem = privateKeyFile.readText()
        .replace("-----BEGIN PRIVATE KEY-----", "")
        .replace("-----END PRIVATE KEY-----", "")
        .replace("\\s".toRegex(), "")

      val keySpecBytes = cleanPem.base64Decode()
      val factory = KeyFactory.getInstance("RSA")

      return factory.generatePrivate(PKCS8EncodedKeySpec(keySpecBytes))
    }

    private fun getPublicKey(): PublicKey {
      val privateKey = getPrivateKey()

      if (privateKey is RSAPrivateCrtKey) {
        val publicKeySpec = RSAPublicKeySpec(privateKey.modulus, privateKey.publicExponent)
        return KeyFactory.getInstance("RSA").generatePublic(publicKeySpec)
      } else {
        throw IllegalArgumentException("The file did not contain a valid RSA CRT Private Key")
      }
    }
  }
}
