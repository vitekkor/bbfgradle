// Original bug: KT-7770

interface SomeFunctionalInterface {
    fun someMethod(arg1: Int, arg2: String)

    companion object {
        inline operator fun invoke(crossinline op: (arg1: Int, arg2: String) -> Unit) =
            object : SomeFunctionalInterface {
                override fun someMethod(arg1: Int, arg2: String) = op(arg1, arg2)
            }
    }
}

fun foo(goo: SomeFunctionalInterface) {
    //...
}

fun main() {
    foo(SomeFunctionalInterface { arg1, arg2 ->
        //...
    })
}

