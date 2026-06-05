package enablebanking

import klite.http.get
import klite.http.httpClient
import java.io.File
import java.net.URI
import java.security.KeyFactory
import java.security.Signature
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64

class EnableBankingClient {
  fun createJwt(): String {
    val privateKeyFile = File("./certs").listFiles().first()
    val iat = System.currentTimeMillis() / 1000

    val header = """{"typ":"JWT","alg":"RS256","kid":"${privateKeyFile.nameWithoutExtension}"}"""
    val payload = """{"iss":"enablebanking.com","aud":"api.enablebanking.com","iat":$iat,"exp":${iat + 3600}}"""

    val unsignedToken = "${encode(header.toByteArray())}.${encode(payload.toByteArray())}"

    val signature = Signature.getInstance("SHA256withRSA")
    signature.initSign(parsePrivateKey(privateKeyFile))
    signature.update(unsignedToken.toByteArray())

    return "$unsignedToken.${encode(signature.sign())}"
  }

  fun parsePrivateKey(file: File): RSAPrivateKey {
    val pemContent = file.readText()
    val cleanPem = pemContent
      .replace("-----BEGIN PRIVATE KEY-----", "")
      .replace("-----END PRIVATE KEY-----", "")
      .replace("\\s".toRegex(), "")

    val keySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(cleanPem))
    return KeyFactory.getInstance("RSA").generatePrivate(keySpec) as RSAPrivateKey
  }

  private fun encode(data: ByteArray) = Base64.getUrlEncoder().withoutPadding().encodeToString(data)
}

suspend fun main() {
  val client = EnableBankingClient()
  val jwt = client.createJwt()
  val http = httpClient()
  val res = http.get(URI("https://api.enablebanking.com/aspsps?country=FI")) {
    header("Authorization", "Bearer $jwt")
  }

  println(res.body())
}
