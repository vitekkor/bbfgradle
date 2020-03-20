//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt


fun A.foo() = (A::bar)(this, "OK")

fun A.bar(x: String) = x

fun box() = A().foo()

