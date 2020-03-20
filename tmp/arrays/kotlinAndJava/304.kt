//File A.java
import kotlin.Metadata;

public final class A {
   public static enum E {
      OK;
   }
}


//File Main.kt


fun box() = A.E.OK.toString()

