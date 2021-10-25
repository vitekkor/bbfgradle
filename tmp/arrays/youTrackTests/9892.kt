// Original bug: KT-7804

fun <T> foo(a: T) = a

class A

fun <T> test(v: T) {
    val a = if (v !is A) {
        foo(v) as T
    }
    else {
        v
    }

    val t: T = a // Error: Type mismatch: inferred type is kotlin.Any? but T was expected
}

fun <T> test2(v: T) {
    val a = if (v !is A) {
        foo(v) as T
    }
    else {
        v as T // Warning: No cast needed
    }

    val t: T = a
}

fun <T> testOk(v: T) {
    val a: T = if (v !is A) {
        foo(v) as T
    }
    else {
        v
    }

    val t: T = a
}

