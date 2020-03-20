//File Outer.java
import kotlin.Metadata;

public class Outer {
   private final int x;

   public final int getX() {
      return this.x;
   }

   private Outer(int x) {
      this.x = x;
   }

   public Outer() {
      this(42);
   }
}


//File Main.kt


fun box(): String {
    val outer = Outer()
    return "OK"
}

