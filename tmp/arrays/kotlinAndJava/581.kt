//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final int x;
   @Nullable
   private final A y;

   public final int getX() {
      return this.x;
   }

   @Nullable
   public final A getY() {
      return this.y;
   }

   public A(int x, @Nullable A y) {
      this.x = x;
      this.y = y;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME

import kotlin.test.assertEquals

fun check(a : A?) : Int {
    return a?.y?.x ?: (a?.x ?: 3)
}

fun checkLeftAssoc(a : A?) : Int {
    return (a?.y?.x ?: a?.x) ?: 3
}

fun box() : String {
    val a1 = A(2, A(1, null))
    val a2 = A(2, null)
    val a3 = null

    assertEquals(1, check(a1))
    assertEquals(2, check(a2))
    assertEquals(3, check(a3))

    assertEquals(1, checkLeftAssoc(a1))
    assertEquals(2, checkLeftAssoc(a2))
    assertEquals(3, checkLeftAssoc(a3))

    return "OK"
}

