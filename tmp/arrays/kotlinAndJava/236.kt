//File X.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class X {
   private boolean hasNext = true;

   public final boolean getHasNext() {
      return this.hasNext;
   }

   public final void setHasNext(boolean var1) {
      this.hasNext = var1;
   }

   public final boolean hasNext(@NotNull It $this$hasNext) {
      boolean var10000;
      if (this.hasNext) {
         this.hasNext = false;
         var10000 = true;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   public final void test() {
      It var2 = (new C()).iterator();

      while(this.hasNext(var2)) {
         int i = var2.next();
         MainKt.foo(i);
      }

   }
}


//File It.java
import kotlin.Metadata;

public final class It {
   public final int next() {
      return 5;
   }
}


//File Main.kt


fun foo(x: Int) {}

fun box(): String {
    X().test()
    return "OK"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C {
   @NotNull
   public final It iterator() {
      return new It();
   }
}
