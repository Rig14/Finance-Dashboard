package enablebanking

import klite.base64UrlEncode
import klite.http.get
import klite.http.httpClient
import java.io.File
import java.net.URI
import java.security.KeyFactory
import java.security.Signature
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64

class Jwt {
  fun createJwt(): String {
    val privateKeyFile = File("./certs").listFiles().first()
    val iat = System.currentTimeMillis() / 1000

    val header = """{"typ":"JWT","alg":"RS256","kid":"${privateKeyFile.nameWithoutExtension}"}"""
    val payload = """{"iss":"enablebanking.com","aud":"api.enablebanking.com","iat":$iat,"exp":${iat + 3600}}"""

    val unsignedToken = "${header.toByteArray().base64UrlEncode()}.${payload.toByteArray().base64UrlEncode()}"

    val signature = Signature.getInstance("SHA256withRSA")
    signature.initSign(parsePrivateKey(privateKeyFile))
    signature.update(unsignedToken.toByteArray())

    return "$unsignedToken.${signature.sign().base64UrlEncode()}"
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
}

suspend fun main() {
  val client = Jwt()
  val jwt = client.createJwt()
  val http = httpClient()
  val res = http.get(URI("https://api.enablebanking.com/aspsps?country=FI")) {
    header("Authorization", "Bearer $jwt")
  }

  println(res.body())
}
