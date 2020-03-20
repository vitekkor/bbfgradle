//File A.java
import java.util.ArrayList;
import kotlin.Metadata;

public final class A extends ArrayList {
}


//File Main.kt


fun box(): String {
    val a = A()
    val b = A()

    a.addAll(b)
    a.addAll(0, b)
    a.removeAll(b)
    a.retainAll(b)
    a.clear()

    a.add("")
    a.set(0, "")
    a.add(0, "")
    a.removeAt(0)
    a.remove("")

    return "OK"
}

