// Original bug: KT-29595

import java.util.function.Supplier

fun main() {
    printAndGetBean {
        object : AbstractBean() {
            override fun value() = 42
        }
    }
}

abstract class AbstractBean {
    abstract fun value(): Int
}

inline fun <reified T : Any> printAndGetBean(crossinline function: () -> T): T {
    println(T::class.java.name)
    return Supplier { function.invoke() }.get()
}
