//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   public static final class Nested {
      @NotNull
      private final String result;

      @NotNull
      public final String getResult() {
         return this.result;
      }

      public Nested(@NotNull String result) {
         super();
         this.result = result;
      }
   }
}


//File Main.kt


fun box() = (A::Nested)("OK").result

