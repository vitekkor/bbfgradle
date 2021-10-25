// Original bug: KT-26063

class Builder<T>(val subject: T)

inline fun <reified E : Throwable> Builder<() -> Unit>.throws() {
    throw RuntimeException()
}

fun expect(subject: ()->Unit) = Builder(subject)

inline fun <reified E : Throwable> throws(noinline action: () -> Unit) {
    throw RuntimeException()
}

class KotlinTest {

    fun test() {
        // ok
        expect{Unit}.throws<RuntimeException>()

        // ok too
        expect{"string"}.throws<RuntimeException>()
    }
}
