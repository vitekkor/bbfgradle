//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public class Outer {
   @NotNull
   private final String outerO = "O";

   @NotNull
   public final String getOuterO() {
      return this.outerO;
   }

   @NotNull
   public final Base test() {
      final String localK = "K";

      final class Local extends Base {
         public Local() {
            super((Function0)(new Function0() {
               @NotNull
               public final String invoke() {
                  return Outer.this.getOuterO() + localK;
               }
            }));
         }
      }

      return (Base)(new Local());
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


fun box() = Outer().test().fn()

