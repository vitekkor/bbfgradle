// Original bug: KT-14442

open class A {
    fun specialPrint(a: Any?) = print(a) // will be used by the extension function
     
    // added extension function
    fun List<*>.show() {
        forEach { specialPrint(it) }
    }
}

class B : A() {
    init {
        listOf("a", "b", "c").show()
    }
}
