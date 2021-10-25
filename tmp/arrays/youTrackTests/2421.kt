// Original bug: KT-36051

class Foo {
    fun bar() {}
}

fun test(foo: Foo) {
    /*caret*/if (foo != null) { // "Replace 'if' expression with safe access expression"
        foo.bar()
    }
}
