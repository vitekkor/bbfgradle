//File A.java
import java.util.HashMap;
import kotlin.Metadata;

public final class A extends HashMap {
}


//File Main.kt


fun box(): String {
    val a = A()
    val b = A()

    a.put("", 0.0)
    a.remove("")

    a.putAll(b)
    a.clear()

    a.keys
    a.values
    a.entries

    return "OK"
}

