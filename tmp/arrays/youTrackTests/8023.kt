// Original bug: KT-23661

package lateinitInCompanionObject

class Test {
    companion object {
        lateinit var buggy: Collection<Int>
        fun access(): Boolean = this::buggy.isInitialized
    }
}

fun main(args: Array<String>) {
    Test.access()
}
