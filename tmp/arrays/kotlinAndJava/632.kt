//File T.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T {
   @NotNull
   String baz();

   public static final class DefaultImpls {
      @NotNull
      public static String baz(T $this) {
         return "T.baz";
      }
   }
}


//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String foo = "OK";

   @NotNull
   public String getFoo() {
      return this.foo;
   }

   @NotNull
   public String bar() {
      return "OK";
   }

   @NotNull
   public String boo() {
      return "OK";
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B extends A implements T {
   @NotNull
   public String bar() {
      return "B";
   }

   @NotNull
   public String baz() {
      return "B.baz";
   }

   public final class E {
      @NotNull
      private final String foo = B.super.getFoo();

      @NotNull
      public final String getFoo() {
         return this.foo;
      }

      @NotNull
      public final String bar() {
         return B.super.bar() + B.super.bar() + T.DefaultImpls.baz(B.this);
      }
   }
}


//File Main.kt


fun box(): String {
    var r = ""

    r = B().E().foo
    if (r != "OK") return "fail 1; r = $r"
    r = ""
    r = B().E().bar()
    if (r != "OKOKT.baz") return "fail 2; r = $r"

    r = ""
    r = C().D().foo
    if (r != "OK") return "fail 3; r = $r"
    r = ""
    r = C().D().bar()
    if (r != "BOK") return "fail 4; r = $r"

    return "OK"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B {
   @NotNull
   public String bar() {
      return "C";
   }

   @NotNull
   public String boo() {
      return "C";
   }

   public final class D {
      @NotNull
      private final String foo = C.super.getFoo();

      @NotNull
      public final String getFoo() {
         return this.foo;
      }

      @NotNull
      public final String bar() {
         return C.super.bar() + C.super.boo();
      }
   }
}
