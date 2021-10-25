// Original bug: KT-35332

class CallableObject {
    operator fun invoke() = Unit
}

class User(
    val name: String
) {
    val callableObject = CallableObject()
    val callableLambda: () -> Unit = {}

    fun checkName() = Unit
}

fun User.extFun() {
    println(name)
    checkName()
    callableLambda()
    callableObject()
}
