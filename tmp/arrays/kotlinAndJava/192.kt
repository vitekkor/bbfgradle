//File M.java
import kotlin.Metadata;

public class M {
   private int b;

   public int getB() {
      return this.b;
   }

   public void setB(int var1) {
      this.b = var1;
   }
}


//File N.java
import kotlin.Metadata;

public final class N extends M {
   private int b = this.getA() + 1;

   public final int getA() {
      super.setB(super.getB() + 1);
      return super.getB() + 1;
   }

   public int getB() {
      return this.b;
   }

   public void setB(int var1) {
      this.b = var1;
   }

   public final int getSuperb() {
      return super.getB();
   }
}


//File Main.kt


fun box(): String {
    val n = N()
    n.a
    n.b
    n.superb
    if (n.b == 3 && n.a == 4 && n.superb == 3) return "OK";
    return "fail";
}

