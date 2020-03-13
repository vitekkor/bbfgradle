//File E.java
import kotlin.Metadata;

public enum E {
   ENTRY;

   public abstract static class Nested {
   }
}


//File Main.kt


fun box(): String {
    E.ENTRY
    return "OK"
}

