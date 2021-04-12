// Original bug: KT-19429

class Manager(provider: DatabaseProvider, val encoder: PasswordEncoder) : UserManager(provider), SignupApi {
    override fun signupNewUser(email: String, password: String) {
        if (getUserByEmail(email) != null) {
            throw EntityExistsException("Such entity already exists")
        }
        val salt = encoder.generateSalt()
    }
}
class DatabaseProvider

class EntityExistsException(error: String) : Throwable(error)

class PasswordEncoder {
    fun generateSalt(){}
}

interface SignupApi {
    fun signupNewUser(email: String, password: String)
}

open class UserManager(val databaseProvider: DatabaseProvider) {
    fun  getUserByEmail(email: String): Any {
        return true
    }
}
