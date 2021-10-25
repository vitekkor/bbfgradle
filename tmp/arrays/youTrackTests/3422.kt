// Original bug: KT-39039

data class MyClass(val field: String)

fun MyClass.f(): String = field

object Ext {
    fun f(): String = "surprise"

    fun String.transform() = toUpperCase()
}

fun MyClass.fTransform() = with(Ext) {
    f().transform()
}

fun main() {
    println(MyClass("foo").fTransform())
}
