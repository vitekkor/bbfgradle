//File Bad.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Bad {
   @NotNull
   private final Function0 a;

   public final int test() {
      return ((Number)this.a.invoke()).intValue();
   }

   public final int invoke() {
      return 2;
   }

   @NotNull
   public final Function0 getA() {
      return this.a;
   }

   public Bad(@NotNull Function0 a) {
      super();
      this.a = a;
   }
}


//File Main.kt
//KT-3189 Function invoke is called with no reason

fun box(): String {

    val bad = Bad({ 1 })

    return if (bad.test() == 1) "OK" else "fail"
}

