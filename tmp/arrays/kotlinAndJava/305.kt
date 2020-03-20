//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt
fun run(arg1: A, arg2: String, funRef:A.(String) -> String): String {
    return arg1.funRef(arg2)
}

fun A.foo(result: String) = result

fun box(): String {
    val x = A::foo
    var r = x(A(), "OK")
    if (r != "OK") return r

    r = run(A(), "OK", A::foo)
    return "OK"
}

