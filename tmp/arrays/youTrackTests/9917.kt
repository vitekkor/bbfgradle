// Original bug: KT-9810

class A {
    val foo = 2
}

fun test(foo: String) {
    with(A()) {
        foo // resolved to parameter for function test, but must be resolved to property foo of class A
    }
}
