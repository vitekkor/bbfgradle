//File IntRange.java
import kotlin.Metadata;

public final class IntRange {
   public final boolean contains(int a) {
      byte var2 = 1;
      return (new kotlin.ranges.IntRange(var2, 2)).contains(a);
   }
}


//File Main.kt



fun box(): String {
    if (2 in C()..2) {
        2 == 2
    }
    return "OK"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C {
   @NotNull
   public final IntRange rangeTo(int i) {
      return new IntRange();
   }
}
