//File M.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class M {
   public final int component1(@NotNull C $this$component1) {
      return $this$component1.getI() + 1;
   }

   public final int component2(@NotNull C $this$component2) {
      return $this$component2.getI() + 2;
   }

   @NotNull
   public final String doTest() {
      String s = "";

      int a;
      int b;
      for(It var3 = (new C(0)).rangeTo(new C(2)).iterator(); var3.hasNext(); s = s + a + ':' + b + ';') {
         C var2 = var3.next();
         a = this.component1(var2);
         b = this.component2(var2);
      }

      return s;
   }
}


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



fun box(): String {
    val s = M().doTest()
    return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   private final int i;

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
