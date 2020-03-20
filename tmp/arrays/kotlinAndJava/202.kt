//File DataClass.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DataClass extends Base {
   @NotNull
   private final String field;

   @NotNull
   public final String getField() {
      return this.field;
   }

   public DataClass(@NotNull String field) {
      super();
      this.field = field;
   }

   @NotNull
   public final String component1() {
      return this.field;
   }

   @NotNull
   public final DataClass copy(@NotNull String field) {
      return new DataClass(field);
   }

   // $FF: synthetic method
   public static DataClass copy$default(DataClass var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.field;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "DataClass(field=" + this.field + ")";
   }

   public int hashCode() {
      String var10000 = this.field;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof DataClass) {
            DataClass var2 = (DataClass)var1;
            if (Intrinsics.areEqual(this.field, var2.field)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}


//File Base.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Base {
   @NotNull
   public String toString() {
      return "Fail";
   }

   public int hashCode() {
      return -42;
   }

   public boolean equals(@Nullable Object other) {
      return false;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// See KT-6206 Always generate hashCode() and equals() for data classes even if base classes have non-trivial analogs

fun box(): String {
    val d = DataClass("x")

    if (d.toString() != "DataClass(field=x)") return "Fail toString"
    if (d.hashCode() != "x".hashCode()) return "Fail hashCode"
    if (d.equals(d) == false) return "Fail equals"

    return "OK"
}

