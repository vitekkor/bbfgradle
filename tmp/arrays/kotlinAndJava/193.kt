//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String v;

   @NotNull
   public String getV() {
      return this.v;
   }

   public A(@NotNull String v) {
      super();
      this.v = v;
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class B {
   @NotNull
   private final String v;

   @NotNull
   public final A a(@NotNull final String newv) {
      return (A)(new A("fail") {
         @NotNull
         private final String v = B.this.getV() + newv;

         @NotNull
         public String getV() {
            return this.v;
         }
      });
   }

   @NotNull
   public String getV() {
      return this.v;
   }

   public B(@NotNull String v) {
      super();
      this.v = v;
   }
}


//File Main.kt


fun box() = B("O").a("K").v

