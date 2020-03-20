//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final Object[] array;

   @NotNull
   public final Object[] getArray() {
      return this.array;
   }

   public A(@NotNull Object[] array) {
      super();
      this.array = array;
   }
}


//File B.java
import kotlin.Metadata;

public final class B extends A {
   public B() {
      super(new Object[]{"OK"});
   }
}


//File Main.kt


fun box() = B().array[0].toString()

