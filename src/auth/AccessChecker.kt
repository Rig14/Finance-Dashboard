package auth

import klite.Before
import klite.ForbiddenException
import klite.HttpExchange
import klite.NotFoundRoute
import klite.RequestMethod.OPTIONS
import users.Jwt
import users.UserRepository
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

@Target(FUNCTION, CLASS) annotation class Public
@Target(FUNCTION, CLASS) annotation class Access

class AccessChecker(private val userRepository: UserRepository): Before {
  override suspend fun before(exchange: HttpExchange) {
    if (exchange.method == OPTIONS) return
    val user = exchange.cookie("jwt")?.let {
      Jwt.validate(it)
      val payload = Jwt.parse(it)
      if (payload.userId == null) null else userRepository.get(payload.userId)
    }
    exchange.attr("user", user)
    val access = exchange.route.findAnnotation<Access>()
    val isPublic = access == null && exchange.route.hasAnnotation<Public>() || exchange.route is NotFoundRoute
    if (user == null && !isPublic) throw ForbiddenException()
    if (user != null && !isPublic) {
      if (access == null) error("@Access annotation is required for non-@Public routes")
    }
    if (user != null) userRepository.setAppUser(user)
  }
}
