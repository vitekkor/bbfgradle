// Original bug: KT-18970

object Foo {
    @JvmField
    val abacaba = ""   // <-- "property 'abacaba' can be private"

    @JvmStatic
    fun main(args: Array<String>) {
        println(abacaba)
    }
}
