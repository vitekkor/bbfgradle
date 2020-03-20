//File BaseGeneric.java
import kotlin.Metadata;

public abstract class BaseGeneric {
   private final Object t;

   public abstract void iterate();

   public final Object getT() {
      return this.t;
   }

   public BaseGeneric(Object t) {
      this.t = t;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

fun box(): String {
    val t = Derived(arrayOf("", "", "", ""))
    t.iterate()
    return if (t.test == 1234) "OK" else "Fail: ${t.test}"
}



//File Derived.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Derived extends BaseGeneric {
   private int test;

   public final int getTest() {
      return this.test;
   }

   public final void setTest(int var1) {
      this.test = var1;
   }

   public void iterate() {
      this.test = 0;
      int i = 0;

      for(int var2 = ((Object[])this.getT()).length; i < var2; ++i) {
         this.test = this.test * 10 + i + 1;
      }

   }

   public Derived(@NotNull Object[] array) {
      super(array);
   }
}
