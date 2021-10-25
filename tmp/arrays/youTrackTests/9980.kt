// Original bug: KT-3993

class User(val login : Boolean) {}

fun currentAccess(user: User?) {
    when {
        user == null -> 0
        user.login -> 1 // nullity error
    }
}
