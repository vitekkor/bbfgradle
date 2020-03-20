//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt


fun <T> test(v: T): T {
    val a: T = if (v !is A) v else v
    return a
}

fun box() = test("OK")

