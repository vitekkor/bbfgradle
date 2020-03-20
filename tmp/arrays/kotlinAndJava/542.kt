//File Main.kt


fun box() = "OK"



//File Clazz.java
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Clazz {
   @NotNull
   private static final Object a = new Object() {
      @NotNull
      public final String run(@NotNull String x) {
         return x;
      }
   };
   public static final Clazz.Companion Companion = new Clazz.Companion((DefaultConstructorMarker)null);

   public static final class Companion {
      @NotNull
      public final Object getA() {
         return Clazz.a;
      }

      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
