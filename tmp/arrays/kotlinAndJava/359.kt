//File A.java
import kotlin.Metadata;

public interface A {
   int getA();

   void setA(int var1);

   public static final class DefaultImpls {
      public static int getA(A $this) {
         return 239;
      }

      public static void setA(A $this, int value) {
      }
   }
}


//File B.java
import kotlin.Metadata;

public final class B implements A {
   public int getA() {
      return A.DefaultImpls.getA(this);
   }

   public void setA(int value) {
      A.DefaultImpls.setA(this, value);
   }
}


//File Main.kt
//KT-2206

fun box() : String {
    return if (B().a == 239) "OK" else "fail"
}

