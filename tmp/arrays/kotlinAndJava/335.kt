//File X.java
import kotlin.Metadata;

public final class X {
   private long value;

   public final long getValue() {
      return this.value;
   }

   public final void setValue(long var1) {
      this.value = var1;
   }

   public X(long value) {
      this.value = value;
   }
}


//File Z.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Z {
   private int counter;
   @NotNull
   private X prop = new X(0L);

   public final int getCounter() {
      return this.counter;
   }

   public final void setCounter(int var1) {
      this.counter = var1;
   }

   @NotNull
   public final X getProp() {
      int var10001 = this.counter++;
      return this.prop;
   }

   public final void setProp(@NotNull X a) {
      int var10001 = this.counter++;
      this.prop = a;
   }
}


//File Main.kt


operator fun X.inc(): X {
    this.value++
    return this
}

operator fun X.dec(): X {
    this.value--
    return this
}

fun box(): String {
    var z = Z()
    z.prop++

    if (z.counter != 2) return "fail in postfix increment: ${z.counter} != 2"
    if (z.prop.value != 1.toLong()) return "fail in postfix increment: ${z.prop.value} != 1"

    z = Z()
    z.prop--

    if (z.counter != 2) return "fail in postfix decrement: ${z.counter} != 2"
    if (z.prop.value != -1.toLong()) return "fail in postfix decrement: ${z.prop.value} != -1"

    return "OK"
}

