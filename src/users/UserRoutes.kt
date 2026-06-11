package users

import klite.Email
import klite.Password
import klite.annotations.BodyParam
import klite.annotations.POST
import klite.annotations.Path

@Path("/users")
class UserRoutes(
  private val userRepository: UserRepository
) {
  @POST("/login") fun login(@BodyParam email: Email, @BodyParam password: Password) {
    userRepository.create(User(email), password)
  }
}
