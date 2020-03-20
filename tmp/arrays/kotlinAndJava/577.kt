//File M.java
import kotlin.Metadata;

public interface M {
   int getBackingB();

   void setBackingB(int var1);

   int getB();

   void setB(int var1);

   public static final class DefaultImpls {
      public static int getB(M $this) {
         return $this.getBackingB();
      }

      public static void setB(M $this, int value) {
         $this.setBackingB(value);
      }
   }
}


//File N.java
import kotlin.Metadata;

public final class N implements M {
   private int backingB;
   private int b = this.getA() + 1;

   public int getBackingB() {
      return this.backingB;
   }

   public void setBackingB(int var1) {
      this.backingB = var1;
   }

   public final int getA() {
      M.DefaultImpls.setB(this, M.DefaultImpls.getB(this) + 1);
      return M.DefaultImpls.getB(this) + 1;
   }

   public int getB() {
      return this.b;
   }

   public void setB(int var1) {
      this.b = var1;
   }

   public final int getSuperb() {
      return M.DefaultImpls.getB(this);
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

