//File Outer.java
import kotlin.Metadata;

public final class Outer {
   public static enum Nested {
      O,
      K;
   }
}


//File Main.kt


fun box() = "${Outer.Nested.O}${Outer.Nested.K}"

