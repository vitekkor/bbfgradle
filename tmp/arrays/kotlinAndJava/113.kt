//File Base.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base {
   @NotNull
   private final Function0 fn1;
   @NotNull
   private final Function0 fn2;

   @NotNull
   public final Function0 getFn1() {
      return this.fn1;
   }

   @NotNull
   public final Function0 getFn2() {
      return this.fn2;
   }

   public Base(@NotNull Function0 fn1, @NotNull Function0 fn2) {
      super();
      this.fn1 = fn1;
      this.fn2 = fn2;
   }
}


//File Main.kt


fun box(): String {
    val x = "x"

    class Local(y: String) : Base({ x + y }, { y + x })

    val local = Local("y")
    val z1 = local.fn1()
    val z2 = local.fn2()

    if (z1 != "xy") return "Fail: z1=$z1"
    if (z2 != "yx") return "Fail: z2=$z2"

    return "OK"
}

