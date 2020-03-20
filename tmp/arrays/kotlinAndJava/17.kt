//File MPair.java
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class MPair {
   private final Object first;

   public boolean equals(@Nullable Object o) {
      if (o == null) {
         throw new TypeCastException("null cannot be cast to non-null type MPair<*>");
      } else {
         MPair t = (MPair)o;
         return Intrinsics.areEqual(this.first, t.first);
      }
   }

   public final Object getFirst() {
      return this.first;
   }

   public MPair(Object first) {
      this.first = first;
   }
}


//File Main.kt


fun box(): String {
   val a = MPair("O")
   a.equals(a)
   return "OK"
}

