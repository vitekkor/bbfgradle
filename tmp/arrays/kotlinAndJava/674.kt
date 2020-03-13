//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String value;

   @NotNull
   public final String component1() {
      return this.value;
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public A(@NotNull String value) {
      super();
      this.value = value;
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B {
   @NotNull
   Object component1();
}


//File Main.kt


fun box(): String {
    val c = C("OK")
    val b: B = c
    val a: A = c
    if (b.component1() != "OK") return "Fail 1"
    if (a.component1() != "OK") return "Fail 2"
    return c.component1()
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C extends A implements B {
   public C(@NotNull String value) {
      super(value);
   }
}
