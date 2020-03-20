//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt
var result = "Fail"

operator fun A.inc(s: String = "OK"): A {
    result = s
    return this
}

fun box(): String {
    var a = A()
    a++
    if (result != "OK") return "Fail 1"

    result = "Fail"
    ++a

    return result
}

