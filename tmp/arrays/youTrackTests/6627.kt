// Original bug: KT-28785

interface A<T> { fun test() }

fun <T : A<out T>?> T.foo() {
    if (this != null) {
        this.test() // unsafe call
    }
}

fun <T : A<in Number>?> T.bar() {
    if (this != null) {
        this.test() // unsafe call
    }
}
