//File I.java
import kotlin.Metadata;

public interface I {
}


//File Main.kt


fun <E: I> foo(a: Any?): E? = a as? E

fun box() = foo<I>(null) ?: "OK"

