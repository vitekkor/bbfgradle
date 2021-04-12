// Original bug: KT-6007

interface A {
    val p: Int
}

inline fun <T> call(lambda: () -> T): T {
    return lambda()
}

fun main(args: Array<String>) {
    val x = 1
    val result = call {
        object : A {
            override val p = x
        } as A
    }

    result.p
}
