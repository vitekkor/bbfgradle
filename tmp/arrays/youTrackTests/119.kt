// Original bug: KT-41034

class A {
    val f: () -> Any = { println("f"); 1 }

    val b = B()
    val c = C()
    val d = D()
}

class B {
    operator fun invoke(): Any {
        println("y")
        return 2
    }
}

class C
operator fun C.invoke(): Any {
    println("c")
    return 2
}

class D
operator fun D?.invoke(): Any {
    println("d")
    return 2
}

fun foo(a: A?) {
    // both calls are errors: UNSAFE_IMPLICIT_INVOKE_CALL
    // a?.f() 
    // a?.b()

    // both calls are successfully resolved
    // both fail at runtime with CCE in non-null cases (wrong receiver is used for extension)
    a?.c()
    a?.d()
}
