//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A extends B {
   @NotNull
   private Integer[] foo = new Integer[]{12, 13};

   @NotNull
   public Integer[] getFoo() {
      return this.foo;
   }

   public void setFoo(@NotNull Integer[] var1) {
      this.foo = var1;
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class B {
   @NotNull
   public abstract Integer[] getFoo();

   public abstract void setFoo(@NotNull Integer[] var1);
}


//File Main.kt
fun box(): String {
        A()
        return "OK"
}

