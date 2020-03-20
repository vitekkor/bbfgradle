//File B.java
import kotlin.Metadata;

public final class B {
}


//File Main.kt


fun B.magic() {
}

fun boom(a: Any) {
    when (a) {
        is B -> run(a::magic)
    }
}

fun box(): String {
    boom(B())
    return "OK"
}

