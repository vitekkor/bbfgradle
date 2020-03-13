//File Main.kt


operator fun C.compareTo(o: C) : Int {
    if (this == o) return 0
    if (o >= o) {
        return 1
    }
    return -1
}

fun box() : String = if (C() > C()) "OK" else "FAIL"



//File C.java
import kotlin.Metadata;

public final class C {
}
