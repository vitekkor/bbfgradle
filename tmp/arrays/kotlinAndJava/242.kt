//File Main.kt


fun box(): String {
  val c = C(true)

  // Commented for KT-621
  // return when(c) {
  //  .p => "OK"
  //  else => "fail"
  // }
  return if (c.p) "OK" else "fail"
}



//File C.java
import kotlin.Metadata;

public final class C {
   private final boolean p;

   public final boolean getP() {
      return this.p;
   }

   public C(boolean p) {
      this.p = p;
   }
}
