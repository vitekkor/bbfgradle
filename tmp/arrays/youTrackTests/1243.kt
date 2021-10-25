// Original bug: KT-6007

open class A

inline fun <T> call(lambda: () -> T): T {
    return lambda()
}

fun main(args: Array<String>) {
    val x = 1
    val result = call {
        object : A() {
            val p = x
        }
    }

    result.p
}
