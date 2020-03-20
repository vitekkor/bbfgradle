//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt


fun box(): String {
    var result = "Fail"

    fun A.ext() { result = "OK" }

    val f = A::ext
    f(A())
    return result
}

