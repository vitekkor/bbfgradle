// Original bug: KT-34904

fun main() {
    val x = MyClass.MyObject
}

sealed class MyClass {

    companion object {
        val OBJECTS = setOf(MyObject).also(::println)
    }

    object MyObject : MyClass()
}
