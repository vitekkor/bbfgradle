//File T.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T {
   @NotNull
   String result();
}


//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private final String x;

   @NotNull
   public final T foo() {
      return (T)(new T() {
         @NotNull
         public final String bar() {
            return A.this.getX();
         }

         @NotNull
         public String result() {
            return this.bar();
         }
      });
   }

   @NotNull
   public final String getX() {
      return this.x;
   }

   public A(@NotNull String x) {
      super();
      this.x = x;
   }
}


//File Main.kt


fun box() = A("OK").foo().result()

