// Original bug: KT-39827

fun main() {
    println("Hello, world!!!")
    val userStatus: UserStatus = UserStatus.LoggedInWithGoogle(
        googleUser = UserStatus.LoggedInWithGoogle.User.Real(
            googleId = "12345",
            internalServiceId = "ABCDE"
        )
    )

    if (userStatus is UserStatus.LoggedInWithGoogle.User) { // This can never be true!!!
        println("It's a Google User")
    } else {
        println("It's something else")
    }

    when (userStatus) {
        is UserStatus.LoggedInWithGoogle.User -> println("It's a Google User") // This can never be true!!!
        else -> println("It's something else")
    }
}

sealed class UserStatus {

    object Unknown : UserStatus()

    data class LoggedIn(val userName: String) : UserStatus()

    data class LoggedInWithGoogle(val googleUser: User) : UserStatus() {

        sealed class User {

            object Unknown : User()

            data class Real(val googleId: String, val internalServiceId: String) : User()

        }

    }

}
