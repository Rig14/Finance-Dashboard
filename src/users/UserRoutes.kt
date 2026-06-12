package users

import auth.Access
import klite.Email
import klite.HttpExchange
import klite.NotFoundException
import klite.Password
import klite.annotations.*
import java.time.Instant.now
import kotlin.time.Duration.Companion.days

@Path("/users")
class UserRoutes(
  private val userRepository: UserRepository
) {
  @POST("/signup")
  fun signup(@BodyParam email: Email, @BodyParam password: Password, e: HttpExchange) {
    val user = User(email)
    userRepository.create(user, password)
    val token = Jwt.create(Payload(userId = user.id))
    e.cookie("jwt", token, now().plusSeconds(10.days.inWholeSeconds))
  }

  @POST("/login")
  fun login(@BodyParam email: Email, @BodyParam password: Password, e: HttpExchange) {
    val user = userRepository.byCredentials(email, password) ?: throw NotFoundException("User not found")
    val token = Jwt.create(Payload(userId = user.id))
    e.cookie("jwt", token, now().plusSeconds(10.days.inWholeSeconds))
  }

  @Access
  @GET("/user")
  fun currentUser(@AttrParam user: User) = user
}
