//File Station.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Station {
   @Nullable
   private final String id;
   @NotNull
   private final String name;
   private final int distance;

   @Nullable
   public final String getId() {
      return this.id;
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   public final int getDistance() {
      return this.distance;
   }

   public Station(@Nullable String id, @NotNull String name, int distance) {
      super();
      this.id = id;
      this.name = name;
      this.distance = distance;
   }

   @Nullable
   public final String component1() {
      return this.id;
   }

   @NotNull
   public final String component2() {
      return this.name;
   }

   public final int component3() {
      return this.distance;
   }

   @NotNull
   public final Station copy(@Nullable String id, @NotNull String name, int distance) {
      return new Station(id, name, distance);
   }

   // $FF: synthetic method
   public static Station copy$default(Station var0, String var1, String var2, int var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.id;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.name;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.distance;
      }

      return var0.copy(var1, var2, var3);
   }

   @NotNull
   public String toString() {
      return "Station(id=" + this.id + ", name=" + this.name + ", distance=" + this.distance + ")";
   }

   public int hashCode() {
      String var10000 = this.id;
      int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
      String var10001 = this.name;
      return (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + Integer.hashCode(this.distance);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof Station) {
            Station var2 = (Station)var1;
            if (Intrinsics.areEqual(this.id, var2.id) && Intrinsics.areEqual(this.name, var2.name) && this.distance == var2.distance) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}


//File Main.kt


fun box(): String {
    var result = ""
    // See KT-14399
    listOf(Station("O", "K", 56)).forEachIndexed { i, (id, name, distance) -> result += "$id$name$distance" }
    if (result != "OK56") return "fail: $result"
    return "OK"
}

