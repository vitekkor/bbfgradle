//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

import kotlin.collections.AbstractIterator

fun box() : String {
    var k = ""
    for (x in MyIterator()) {
        k+=x
    }
    return if(k=="01234") "OK" else k
}



//File MyIterator.java
import kotlin.Metadata;
import kotlin.collections.AbstractIterator;

public final class MyIterator extends AbstractIterator {
   private int i;

   public final int getI() {
      return this.i;
   }

   public final void setI(int var1) {
      this.i = var1;
   }

   public void computeNext() {
      if (this.i < 5) {
         int var1;
         this.i = (var1 = this.i) + 1;
         this.setNext(String.valueOf(var1));
      }

   }
}
