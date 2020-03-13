//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt


fun box() = if ((A::equals)(A(), A())) "Fail" else "OK"

