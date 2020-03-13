//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME

import kotlin.reflect.*

var pr = Box("first")

fun box(): String {
    val p = ::pr
    if (p.get().value != "first") return "Fail value 1: ${p.get()}"
    if (p.name != "pr") return "Fail name: ${p.name}"
    p.set(Box("second"))
    if (p.get().value != "second") return "Fail value 2: ${p.get()}"
    return "OK"
}



//File Box.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Box {
   @NotNull
   private final String value;

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public Box(@NotNull String value) {
      super();
      this.value = value;
   }

   @NotNull
   public final String component1() {
      return this.value;
   }

   @NotNull
   public final Box copy(@NotNull String value) {
      return new Box(value);
   }

   // $FF: synthetic method
   public static Box copy$default(Box var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.value;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "Box(value=" + this.value + ")";
   }

   public int hashCode() {
      String var10000 = this.value;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof Box) {
            Box var2 = (Box)var1;
            if (Intrinsics.areEqual(this.value, var2.value)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
