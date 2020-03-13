//File Main.kt


fun box(): String {
  val c1 = C(10)
  val c2 = C(10)
  return if (c1 == c2) "OK" else "fail"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class C {
   private final int x;

   public boolean equals(@Nullable Object rhs) {
      if (rhs instanceof C) {
         C rhsC = (C)rhs;
         return rhsC.x == this.x;
      } else {
         return false;
      }
   }

   public final int getX() {
      return this.x;
   }

   public C(int x) {
      this.x = x;
   }
}
