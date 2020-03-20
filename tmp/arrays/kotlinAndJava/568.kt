//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Outer {
   @Nullable
   private final Object prop;
   private final Object v;

   @Nullable
   public final Object getProp() {
      return this.prop;
   }

   public final Object getV() {
      return this.v;
   }

   public Outer(Object v) {
      this.v = v;

      final class Inner {
         private final Object v;

         @NotNull
         public String toString() {
            return String.valueOf(this.v);
         }

         public final Object getV() {
            return this.v;
         }

         public Inner(Object v) {
            this.v = v;
         }
      }

      Inner value = new Inner(this.v);
      this.prop = value;
   }
}


//File Main.kt
// !DIAGNOSTICS: -UNUSED_VARIABLE
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String {
    return Outer("OK").prop.toString()
}

