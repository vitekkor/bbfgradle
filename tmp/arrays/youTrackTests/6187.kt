// Original bug: KT-25302

interface CollectorMock<A, R>

interface StreamMock {
    fun <R, A> collect(collector: CollectorMock<A, R>): R
}

fun <T> accept(s: T) {}
fun ofK(t: String): StreamMock = TODO()
fun <T> toSetK(): CollectorMock<*, T> = TODO()

class KotlinCollectionUser {
    fun use() {
        accept(ofK("").collect(toSetK<String>()))
    }
}
