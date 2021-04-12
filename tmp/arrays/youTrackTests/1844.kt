// Original bug: KT-43631

fun boo() {
    println("boo")
}

inline fun foo() {
    @Suppress("NOT_YET_SUPPORTED_IN_INLINE")
    fun bar() {
        println("bar")
    }

    (::boo)();
    { println("lambda") }()
    bar()
}

fun test1() {
    foo()
}

fun test2() {
    foo()
}

inline fun baz(f: () -> Unit) {
    f()
    f()
}

fun test3() {
    baz {
        fun qux() {
            println("qux")
        }

        qux()
        (::qux)();
        { println("lambda2") }()
    }
}

fun main() {
    test1()
    test2()
    foo()
    foo()
    test3()
}
