// Original bug: KT-26718

package p1

interface IFoo {
    fun foo()
}

class C {
    private companion object : IFoo {
        override fun foo() {
            println("I'm 'foo' in private companion object of p1.C")
        }
    }
}
