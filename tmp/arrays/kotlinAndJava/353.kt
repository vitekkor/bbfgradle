//File MyClass.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class MyClass {
   @NotNull
   private Function0 fnc;

   @NotNull
   public final String test() {
      return (String)this.fnc.invoke();
   }

   @NotNull
   public final Function0 getFnc() {
      return this.fnc;
   }

   public final void setFnc(@NotNull Function0 var1) {
      this.fnc = var1;
   }

   public MyClass(@NotNull Function0 fnc) {
      super();
      this.fnc = fnc;
   }
}


//File Main.kt


fun printtest() : String {
    return "OK"
}

fun box(): String {
    var c = MyClass({ printtest() })

    return c.test()
}

