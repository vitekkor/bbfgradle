//File TestEnumClass.java
import kotlin.Metadata;

public enum TestEnumClass {
   ZERO;

   private final int x;

   public final int getX() {
      return this.x;
   }

   private TestEnumClass(int x) {
      this.x = x;
   }

   private TestEnumClass() {
      this(0);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
//WITH_RUNTIME
import kotlin.test.assertEquals

fun box(): String {
    assertEquals(TestEnumClass.ZERO.x, 0)

    return "OK"
}

