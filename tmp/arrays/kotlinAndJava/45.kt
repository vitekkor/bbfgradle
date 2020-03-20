//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String ok = "OK";

   @NotNull
   public final String getOk() {
      return this.ok;
   }

   @NotNull
   public final String foo() {
      final class Local extends Base {
         public Local() {
            super((Function0)(new Function0() {
               @NotNull
               public final String invoke() {
                  return Outer.this.getOk();
               }
            }));
         }
      }

      return (String)(new Local()).getFn().invoke();
   }
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public abstract class Base {
   @NotNull
   private final Function0 fn;

   @NotNull
   public final Function0 getFn() {
      return this.fn;
   }

   public Base(@NotNull Function0 fn) {
      super();
      this.fn = fn;
   }
}


//File Main.kt


fun box() = Outer().foo()

