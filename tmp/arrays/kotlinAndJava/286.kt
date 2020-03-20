//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String foo = "Foo";

   @NotNull
   public final String getFoo() {
      return this.foo;
   }

   @NotNull
   public final String id(@NotNull final String $this$id) {

      final class Local {
         @NotNull
         public final String result() {
            return $this$id;
         }

         @NotNull
         public final Outer outer() {
            return Outer.this;
         }

         public Local(long unused) {
         }
      }

      Local l = new Local(42L);
      return l.result() + l.outer().foo;
   }

   @NotNull
   public final String result() {
      return this.id("OK");
   }
}


//File Main.kt


fun box(): String {
    val r = Outer().result()

    if (r != "OKFoo") return "Fail: $r"

    return "OK"
}

