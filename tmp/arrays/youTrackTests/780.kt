// Original bug: KT-42858

class WithNative {
    companion object {
        val foo: Int = 1
            @JvmStatic external get
    }
}

object ObjWithNative {
    val foo: Int
        @JvmStatic external get
}

fun main() {
    try {
        (WithNative.Companion::foo).getter()
        println("no link error 1")
    } catch (e: Throwable) {
        println(e.cause?.message ?: e.message)
    }

    try {
        (ObjWithNative::foo).getter()
        println("no link error 2")
    } catch (e: Throwable) {
        println(e.cause?.message ?: e.message)
    }
}
