// Original bug: KT-3993

class u(val login : Boolean) {}
fun f1() : u? = null
fun f2(b : ()->Int) : Int = b()
fun currentAccess(): Int = f2 {
    val user = f1()
    when {
        user == null -> 0
        user.login -> 1 // nullity error
        else -> 2
    }
}
