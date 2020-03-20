//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String x;
   @NotNull
   private final String z;

   @NotNull
   public final String getX() {
      return this.x;
   }

   @NotNull
   public final String getZ() {
      return this.z;
   }

   public A(@NotNull String x, @NotNull String z) {
      super();
      this.x = x;
      this.z = z;
   }

   public A(@NotNull String z) {
      this("O", z);
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   @NotNull
   private final String y;

   @NotNull
   public final String getY() {
      return this.y;
   }

   public B(@NotNull String y) {
      super("_");
      this.y = y;
   }
}


//File Main.kt


fun box(): String {
    val b = B("K")
    val result = b.z + b.x + b.y
    if (result != "_OK") return "fail: $result"
    return "OK"
}

