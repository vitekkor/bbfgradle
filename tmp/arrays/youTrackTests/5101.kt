// Original bug: KT-34413

object Foo {
    fun foo(a: MyString, b: MyString) = a.value + b.value
}

class MyString {
    var value: String = ""

    fun self() = this
}

class Goo {
    fun f1(): String {
        val a = "a"
        val b = "b"

        return Foo.foo(
            MyString().apply { value = a },
            MyString()
        )
    }

    fun f2(): String {
        val a = "a"
        val b = "b"

        return Foo.foo(
            MyString().apply { value = a },
            MyString()
        )
    }
}
