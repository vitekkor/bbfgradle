//File A.java
import kotlin.Metadata;

public final class A {
}


//File B.java
import kotlin.Metadata;

public final class B {
}


//File Main.kt


fun box(): String {
    val a = A()
    a as? B
    a as? B ?: "fail"

    if ((A() as? B) != null) return "fail1"
    if ((a as? B) != null) return "fail2"

    val v = a as? B ?: "fail"
    if (v != "fail") return "fail4"

    return "OK"
}

