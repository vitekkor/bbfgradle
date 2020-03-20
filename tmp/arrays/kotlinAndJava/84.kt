//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

fun box(): String {
    return Box("OK").extract()
}



//File Box.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;

public final class Box {
   @NotNull
   private final String s;

   @NotNull
   public final String extract() {
      final ObjectRef result = new ObjectRef();
      result.element = "";
      ((Runnable)(new Runnable() {
         public final void run() {
            result.element = Box.this.getS();
         }
      })).run();
      return (String)result.element;
   }

   @NotNull
   public final String getS() {
      return this.s;
   }

   public Box(@NotNull String s) {
      super();
      this.s = s;
   }
}
