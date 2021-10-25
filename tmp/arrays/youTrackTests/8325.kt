// Original bug: KT-21988

package testinline

class DomainException(override val message: String = ""): Throwable(message)

inline fun verify(test: Boolean, lazyMessage: ()->Any = { "" }) {
    if(!test) {
        throw DomainException(lazyMessage().toString())
    }
}

fun main(args: Array<String>) {
    verify(true)
}
