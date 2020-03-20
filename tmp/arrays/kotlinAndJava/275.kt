//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt


fun box(): String {
    fun A.foo() = "OK"
    return (A::foo)(A())
}

