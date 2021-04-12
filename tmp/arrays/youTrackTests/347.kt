// Original bug: KT-45127

import kotlin.coroutines.cancellation.CancellationException

interface Foo {
    @Throws(CancellationException::class)
    suspend fun foo()
}

suspend fun callFoo(foo: Foo) {
    try {
        foo.foo()
    } catch (e: Throwable) {
        println(e::class)
        println(e.toString())
    }
}

@Throws(Throwable::class)
fun rethrow(exception: Throwable): Nothing {
    throw exception
}
