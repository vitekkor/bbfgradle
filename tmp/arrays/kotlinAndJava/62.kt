//File M.java
import kotlin.Metadata;

public class M {
   public int x() {
      return 10;
   }
}


//File BK.java
import kotlin.Metadata;

public interface BK {
   int x();

   public static final class DefaultImpls {
      public static int x(BK $this) {
         return 50;
      }
   }
}


//File K.java
import kotlin.Metadata;

public interface K extends BK {
   int x();

   public static final class DefaultImpls {
      public static int x(K $this) {
         return BK.DefaultImpls.x((BK)$this) * 2;
      }
   }
}


//File N.java
import kotlin.Metadata;

public class N extends M implements K {
   public int x() {
      return 20;
   }

   public class C implements K {
      public final int test1() {
         return this.x();
      }

      public final int test2() {
         return N.super.x();
      }

      public final int test3() {
         return K.DefaultImpls.x(N.this);
      }

      public final int test4() {
         return K.DefaultImpls.x(this);
      }

      public int x() {
         return K.DefaultImpls.x(this);
      }
   }
}


//File Main.kt


fun box(): String {
    if (N().C().test1() != 100) return "test1 fail";
    if (N().C().test2() != 10)  return "test2 fail";
    if (N().C().test3() != 100) return "test3 fail";
    if (N().C().test4() != 100) return "test4 fail";
    return "OK";
}

