// Original bug: KT-39827

import UserStatus.LoggedInWithGoogle.User

fun main() {
    val userStatus: UserStatus = UserStatus.LoggedInWithGoogle(
        UserStatus.LoggedInWithGoogle.User.Real(
            name = "sdf"
        )
    )

    if (userStatus is User) { // This can never be true!!!
        println("It's a Google User")
    } else {
        println("It's something else")
    }
}

sealed class UserStatus {
    data class LoggedInWithGoogle(val user: User) : UserStatus() {
        sealed class User {
            data class Real(val name: String) : User()
        }
    }
}
