//File B.java
import kotlin.Metadata;

public final class B {
   private final int x;

   public final void foo() {
      final class X extends Z {
         public X() {
            super(B.this.getX());
         }
      }

      new X();
   }

   public final int getX() {
      return this.x;
   }

   public B(int x) {
      this.x = x;
   }
}


//File Z.java
import kotlin.Metadata;

public class Z {
   private final int s;

   public void a() {
   }

   public final int getS() {
      return this.s;
   }

   public Z(int s) {
      this.s = s;
   }
}


//File Main.kt


fun box(): String {
    B(1).foo()
    return "OK"
}

