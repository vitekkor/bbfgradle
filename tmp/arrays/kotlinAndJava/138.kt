//File A.java
import kotlin.Metadata;

public final class A {
   private final int t;

   public final int getT() {
      return this.t;
   }

   public A(int t) {
      this.t = t;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
//KT-1572 Frontend doesn't mark all vars included in closure as refs.

fun testKt1572() : Boolean {
    var a = A(0)
    var b = A(3)
    val changer = {a = b}
    b = A(10) // this change has no effect on changer
    changer()
    return (a.t == 10)
}

fun testPrimitives() : Boolean {
    var a = 0
    var b = 3
    val changer = {a = b}
    b = 10
    changer()
    return (a == 10)
}

fun box() = if (testKt1572() && testPrimitives()) "OK" else "fail"

