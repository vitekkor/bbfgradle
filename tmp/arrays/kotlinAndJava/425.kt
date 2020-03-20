//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Outer {
   @NotNull
   private final char[] chars;

   public final String value() {
      return (new Outer.Inner("OK")).concat();
   }

   @NotNull
   public final char[] getChars() {
      return this.chars;
   }

   public Outer(@NotNull char... chars) {
      super();
      this.chars = chars;
   }

   public class Inner extends Outer {
      @NotNull
      private final String s;

      public final String concat() {
         return String.valueOf(this.getChars());
      }

      @NotNull
      public final String getS() {
         return this.s;
      }

      public Inner(@NotNull String s) {
         super(s.charAt(0), s.charAt(1));
         this.s = s;
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// When inner class extends its outer, there are two instances of the outer present in the inner:
// the enclosing one and the one in the super call.
// Here we test that symbols are resolved to the instance created via the super call.

fun box() = Outer('F', 'a', 'i', 'l').value()

