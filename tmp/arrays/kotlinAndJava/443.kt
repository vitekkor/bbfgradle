//File X.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class X {
   public final int next(@NotNull It $this$next) {
      return 5;
   }

   public final void test() {
      It var2 = (new C()).iterator();

      while(var2.hasNext()) {
         int i = this.next(var2);
         MainKt.foo(i);
      }

   }
}


//File It.java
import kotlin.Metadata;

public final class It {
   private boolean hasNext = true;

   public final boolean getHasNext() {
      return this.hasNext;
   }

   public final void setHasNext(boolean var1) {
      this.hasNext = var1;
   }

   public final boolean hasNext() {
      boolean var10000;
      if (this.hasNext) {
         this.hasNext = false;
         var10000 = true;
      } else {
         var10000 = false;
      }

      return var10000;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

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
