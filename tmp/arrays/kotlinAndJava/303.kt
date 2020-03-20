//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   public Outer(@NotNull String x) {
      super();
      this.x = x;
   }

   public final class Inner {
      @NotNull
      private final String z;
      @NotNull
      private final String y;

      @NotNull
      public final String getZ() {
         return this.z;
      }

      @NotNull
      public final String getY() {
         return this.y;
      }

      public Inner(@NotNull String y) {
         super();
         this.y = y;
         this.z = Outer.this.getX() + this.y;
      }
   }
}


//File Main.kt


fun box() = Outer("O").Inner("K").z

