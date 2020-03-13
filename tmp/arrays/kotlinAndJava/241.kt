//File A.java
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   public final boolean add3(@NotNull ArrayList $this$add3, Object el) {
      return $this$add3.add(el);
   }

   public final void test(@NotNull ArrayList list) {
      int i = 1;

      for(byte var3 = 10; i <= var3; ++i) {
         this.add3(list, i);
      }

   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// WITH_RUNTIME
// KJS_WITH_FULL_RUNTIME

infix fun <T> ArrayList<T>.add2(el: T) = add(el)

fun box() : String{
    var list = ArrayList<Int>()
    for (i in 1..10) {
      list.add(i)
      list add2 i
    }
    A().test(list)
    println(list)
    return "OK"
}

