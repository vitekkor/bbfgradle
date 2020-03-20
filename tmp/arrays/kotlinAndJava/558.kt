//File It.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class It {
   private int c;
   @NotNull
   private final C from;
   @NotNull
   private final C to;

   public final int getC() {
      return this.c;
   }

   public final void setC(int var1) {
      this.c = var1;
   }

   @NotNull
   public final C next() {
      C next = new C(this.c);
      int var10001 = this.c++;
      return next;
   }

   public final boolean hasNext() {
      return this.c <= this.to.getI();
   }

   @NotNull
   public final C getFrom() {
      return this.from;
   }

   @NotNull
   public final C getTo() {
      return this.to;
   }

   public It(@NotNull C from, @NotNull C to) {
      super();
      this.from = from;
      this.to = to;
      this.c = this.from.getI();
   }
}


//File Range.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Range {
   @NotNull
   private final C from;
   @NotNull
   private final C to;

   @NotNull
   public final It iterator() {
      return new It(this.from, this.to);
   }

   @NotNull
   public final C getFrom() {
      return this.from;
   }

   @NotNull
   public final C getTo() {
      return this.to;
   }

   public Range(@NotNull C from, @NotNull C to) {
      super();
      this.from = from;
      this.to = to;
   }
}


//File Main.kt


fun doTest(): String {
    var s = ""
    for ((a, _) in C(0)..C(2)) {
        s += "$a;"
    }

    for ((_, b) in C(1)..C(3)) {
        s += "$b;"
    }

    for ((_, `_`) in C(2)..C(4)) {
        s += "$`_`;"
    }

    return s
}

fun box(): String {
    val s = doTest()
    return if (s == "1;2;3;3;4;5;4;5;6;") "OK" else "fail: $s"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   private final int i;

   public final int component1() {
      return this.i + 1;
   }

   public final int component2() {
      return this.i + 2;
   }

   @NotNull
   public final Range rangeTo(@NotNull C c) {
      return new Range(this, c);
   }

   public final int getI() {
      return this.i;
   }

   public C(int i) {
      this.i = i;
   }
}
