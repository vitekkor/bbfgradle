//File Test.java
import kotlin.Metadata;

public final class Test {
   private final int y;
   private final int z;
   private final int x;

   public final int getY() {
      return this.y;
   }

   public final int getZ() {
      return this.z;
   }

   public final int getX() {
      return this.x;
   }

   public Test(int x) {
      this.x = x;
      this.y = this.x + 1;
      this.z = this.y + 1;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
//WITH_RUNTIME
import kotlin.test.assertEquals

fun box(): String {
    val test = Test(1)
    assertEquals(test.x, 1)
    assertEquals(test.y, 2)
    assertEquals(test.z, 3)

    return "OK"
}

