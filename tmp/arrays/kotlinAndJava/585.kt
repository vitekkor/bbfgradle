//File E.java
import kotlin.Metadata;

public enum E {
   ENTRY;
}


//File Main.kt


fun box(): String {
    val f = E::valueOf
    val result = f("ENTRY")
    return if (result == E.ENTRY) "OK" else "Fail $result"
}

