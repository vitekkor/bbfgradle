//File T.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T {
   @NotNull
   String getBaz();

   public static final class DefaultImpls {
      @NotNull
      public static String getBaz(T $this) {
         return "T.baz";
      }
   }
}


//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public String getBar() {
      return "OK";
   }

   @NotNull
   public String getBoo() {
      return "OK";
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B extends A implements T {
   @NotNull
   public String getBar() {
      return "B";
   }

   @NotNull
   public String getBaz() {
      return "B.baz";
   }

   public final class E {
      @NotNull
      public final String getBar() {
         return B.super.getBar() + B.super.getBar() + T.DefaultImpls.getBaz(B.this);
      }
   }
}


//File Main.kt


fun box(): String {
    var r = ""

    r = B().E().bar
    if (r != "OKOKT.baz") return "fail 1; r = $r"

    r = C().D().bar
    if (r != "BOK") return "fail 2; r = $r"

    return "OK"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B {
   @NotNull
   public String getBar() {
      return "C";
   }

   @NotNull
   public String getBoo() {
      return "C";
   }

   public final class D {
      @NotNull
      public final String getBar() {
         return C.super.getBar() + C.super.getBoo();
      }
   }
}
