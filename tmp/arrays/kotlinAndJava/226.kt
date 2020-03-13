//File T4.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class T4 {
   private final boolean c1;
   private final boolean c2;
   private final boolean c3;
   @NotNull
   private final String c4;

   public boolean equals(@Nullable Object o) {
      if (!(o instanceof T4)) {
         return false;
      } else {
         return this.c1 == ((T4)o).c1 && this.c2 == ((T4)o).c2 && this.c3 == ((T4)o).c3 && Intrinsics.areEqual(this.c4, ((T4)o).c4);
      }
   }

   public final boolean getC1() {
      return this.c1;
   }

   public final boolean getC2() {
      return this.c2;
   }

   public final boolean getC3() {
      return this.c3;
   }

   @NotNull
   public final String getC4() {
      return this.c4;
   }

   public T4(boolean c1, boolean c2, boolean c3, @NotNull String c4) {
      super();
      this.c1 = c1;
      this.c2 = c2;
      this.c3 = c3;
      this.c4 = c4;
   }
}


//File Main.kt


fun reformat(
  str : String,
  normalizeCase : Boolean = true,
  uppercaseFirstLetter : Boolean = true,
  divideByCamelHumps : Boolean = true,
  wordSeparator : String = " "
) =
  T4(normalizeCase, uppercaseFirstLetter, divideByCamelHumps, wordSeparator)


fun box() : String {
    val expected = T4(true, true, true, " ")
    if(reformat("", true, true, true, " ") != expected) return "fail"
    if(reformat("", true, true, true) != expected) return "fail"
    if(reformat("", true, true) != expected) return "fail"
    if(reformat("", true) != expected) return "fail"
    if(reformat("") != expected) return "fail"
    return "OK"
}

