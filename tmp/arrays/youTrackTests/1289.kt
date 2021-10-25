// Original bug: KT-29595

fun main(args: Array<String>) {
    foo {
        object {}
    }
}

inline fun <reified T : Any> foo(crossinline function: () -> T) {
    println(T::class.java.name)

    object {
        fun bar() {
            function()
        }
    }
}
