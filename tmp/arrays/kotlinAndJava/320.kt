//File A1.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A1 {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   public A1(@NotNull String y) {
      super();
      this.x = "A1.x," + y;
   }
}


//File Main.kt


fun box(): String {
    val r1 = A2("c").foo()
    if (r1 != "A1.x,B1.param,q;A2.x,B2.param,w;A1.x,B1.param,B3.param,e;A2.x,c") return "fail1: $r1"

    val r2 = A3("d").foo()
    if (r2 != "A1.x,B1.param,q;A3.x,B2.param,w;A1.x,B1.param,B3.param,e;A3.x,d") return "fail2: $r2"

    return "OK"
}



//File A3.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A3 {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   @NotNull
   public final String foo() {
      return (new A3.B1("q")).getX() + ";" + (new A3.B2("w")).getX() + ";" + (new A3.B3("e")).getX() + ";" + this.x;
   }

   public A3(@NotNull String y) {
      super();
      this.x = "A3.x," + y;
   }

   public class B1 extends A1 {
      public B1(@NotNull String p) {
         super("B1.param," + p);
      }
   }

   public class B2 extends A3 {
      public B2(@NotNull String p) {
         super("B2.param," + p);
      }
   }

   public final class B3 extends A3.B1 {
      public B3(@NotNull String p) {
         super("B3.param," + p);
      }
   }
}


//File A2.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A2 {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   @NotNull
   public final String foo() {
      return (new A2.B1("q")).getX() + ";" + (new A2.B2("w")).getX() + ";" + (new A2.B3("e")).getX() + ";" + this.x;
   }

   public A2(@NotNull String y) {
      super();
      this.x = "A2.x," + y;
   }

   public class B1 extends A1 {
      public B1(@NotNull String p) {
         super("B1.param," + p);
      }
   }

   public class B2 extends A2 {
      public B2(@NotNull String p) {
         super("B2.param," + p);
      }
   }

   public final class B3 extends A2.B1 {
      public B3(@NotNull String p) {
         super("B3.param," + p);
      }
   }
}
