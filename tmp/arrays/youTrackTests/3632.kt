// Original bug: KT-11567

package test.privateCompanionObject

interface IFoo {
    fun foo()
}

class KHost {
    private companion object : IFoo {
        override fun foo() {
            println("foo in private KHost.Companion")
        }
    }
}
