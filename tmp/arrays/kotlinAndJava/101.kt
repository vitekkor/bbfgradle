//File D.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class D {
   @NotNull
   private final String s;

   @NotNull
   public final String getS() {
      return this.s;
   }

   public D(@NotNull String s) {
      super();
      this.s = s;
   }
}


//File E.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class E {
   public final int foo() {
      return 1;
   }

   @NotNull
   public final E bar() {
      return this;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
fun t1() : Boolean {
    val s1 : String? = "sff"
    val s2 : String? = null
    return s1?.length == 3 && s2?.length == null
}

fun t2() : Boolean {
    val c1: C? = C(1)
    val c2: C? = null
    return c1?.x == 1 && c2?.x == null
}

fun t3() {
    val d: D = D("s")
    val x = d?.s
    if (!(d?.s == "s")) throw AssertionError()
}

fun t4() {
    val e: E? = E()
    if (!(e?.bar() == e)) throw AssertionError()
    val x = e?.foo()
}

fun box() : String {
    if(!t1 ()) return "fail"
    if(!t2 ()) return "fail"
    t3()
    t4()
    return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C {
   private final int x;

   public final int getX() {
      return this.x;
   }

   public C(int x) {
      this.x = x;
   }
}
