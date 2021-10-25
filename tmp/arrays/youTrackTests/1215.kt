// Original bug: KT-8970

fun main() {
    val x = MyClass.MyObject
}

sealed class MyClass {

    companion object {
        val OBJECTS = setOf(MyObject).also(::println)
    }

    object MyObject : MyClass()
}
