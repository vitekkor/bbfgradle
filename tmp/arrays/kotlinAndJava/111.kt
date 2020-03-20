//File Foo.java
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Foo extends Base {
   @NotNull
   public final String something() {
      String var1 = this.getBar();
      boolean var2 = false;
      if (var1 == null) {
         throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
      } else {
         String var10000 = var1.toUpperCase();
         return var10000;
      }
   }

   public Foo(@NotNull String bar) {
      super(bar);
   }
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base {
   @NotNull
   private final String bar;

   @NotNull
   public final String getBar() {
      return this.bar;
   }

   public Base(@NotNull String bar) {
      super();
      this.bar = bar;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

fun box() = Foo("ok").something()

