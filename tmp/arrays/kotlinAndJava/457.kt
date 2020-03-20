//File A.java
import kotlin.Metadata;

public final class A implements In {
}


//File B.java
import kotlin.Metadata;

public final class B implements In {
}


//File Main.kt

fun <T> select(x: T, y: T) = x ?: y

// This test just checks that no internal error happens in backend
fun foobar(a: A, b: B) = select(a, b)

fun box() = "OK"



//File In.java
import kotlin.Metadata;

public interface In {
}
