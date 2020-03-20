//File Main.kt


fun box(): String {
  val c = C()
  if (c.f != 610) return "fail"
  return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C {
   private int f = 610;

   public final int getF() {
      return this.f;
   }

   public final void setF(int var1) {
      this.f = var1;
   }
}
