//File T.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T {
   @NotNull
   String result();
}


//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A extends B {
   @NotNull
   public final T foo() {
      return (T)(new T() {
         @NotNull
         private final String bar = A.this.getX();

         @NotNull
         public final String getBar() {
            return this.bar;
         }

         @NotNull
         public String result() {
            return this.bar;
         }
      });
   }

   public A() {
      super("OK");
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class B {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   public B(@NotNull String x) {
      super();
      this.x = x;
   }
}


//File Main.kt


fun box() = A().foo().result()

