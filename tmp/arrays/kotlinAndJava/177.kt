//File DataClass.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

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
}


//File Base.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Base {
   @NotNull
   public final String toString() {
      return "OK";
   }

   public final int hashCode() {
      return 42;
   }

   public final boolean equals(@Nullable Object other) {
      return false;
   }
}


//File Main.kt


fun box(): String {
    val d = DataClass("x")

    if (d.toString() != "OK") return "Fail toString"
    if (d.hashCode() != 42) return "Fail hashCode"
    if (d.equals(d) != false) return "Fail equals"

    return "OK"
}

