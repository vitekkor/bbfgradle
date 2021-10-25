// Original bug: KT-26760

open class A

class B : A()

fun collect(): Collection<B> {
    return source()
            .mapNotNull {
                if (it is B) {
                    return@mapNotNull it as B
                }
                null
            }
}

private fun source(): Collection<A> = emptyList()
