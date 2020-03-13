//File A.java
import kotlin.Metadata;

public enum A {
   ONE,
   TWO;

   public final int invoke(int i) {
      return i;
   }
}


//File Main.kt


fun box() = if (A.ONE(42) == 42) "OK" else "fail"

