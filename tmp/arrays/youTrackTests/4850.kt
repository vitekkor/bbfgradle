// Original bug: KT-11684

interface Parent {
    fun foo(): Int
    val bar: String
}
abstract class Son : Parent {
    override fun foo() /*: Int,    not Nothing */ = throw UnsupportedOperationException()
    override val bar   /*: String, not Nothing */ = throw UnsupportedOperationException()
}
