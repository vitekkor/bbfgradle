//File M.java
import kotlin.Metadata;

public class M {
   private int y = 500;

   public int getY() {
      return this.y;
   }

   public void setY(int var1) {
      this.y = var1;
   }
}


//File N.java
import kotlin.Metadata;

public class N extends M {
   private int y = 200;

   public int getY() {
      return this.y;
   }

   public void setY(int var1) {
      this.y = var1;
   }

   public class C {
      public final int test5() {
         return N.this.getY();
      }

      public final int test6() {
         N var10000 = N.this;
         var10000.setY(var10000.getY() + 200);
         return N.super.getY();
      }
   }
}


//File Main.kt


fun box(): String {
    if (N().C().test5() != 200) return "test5 fail";
    if (N().C().test6() != 700) return "test6 fail";
    return "OK";
}

