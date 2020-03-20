//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private final String _kind;
   @NotNull
   private final String p;

   @NotNull
   public final String get_kind() {
      return this._kind;
   }

   @NotNull
   public final String getP() {
      return this.p;
   }

   public A(@NotNull String p) {
      super();
      this.p = p;
      this._kind = String.valueOf(this.p);
   }
}


//File Main.kt


fun box(): String {

    if (A("OK")._kind != "OK") return "fail"

    return "OK"
}

