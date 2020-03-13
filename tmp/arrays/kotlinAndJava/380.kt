//File I.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface I {
   @NotNull
   String getFoo(@NotNull String var1);

   @NotNull
   String bar();

   public static final class DefaultImpls {
      @NotNull
      public static String getFoo(I $this, @NotNull String $this$foo) {
         return $this$foo + ";" + $this.bar();
      }
   }
}


//File Main.kt


fun box(): String {
    val r = C().test()
    if (r != "test;C.bar") return "fail: $r"

    return "OK"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C implements I {
   @NotNull
   public String bar() {
      return "C.bar";
   }

   @NotNull
   public final String test() {
      return this.getFoo("test");
   }

   @NotNull
   public String getFoo(@NotNull String $this$foo) {
      return I.DefaultImpls.getFoo(this, $this$foo);
   }
}
