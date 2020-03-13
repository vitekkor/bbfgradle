//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A extends B {
   public A() {
      super("default");
   }

   public A(@NotNull String x) {
      super(x, "default");
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B {
   private final Object x;
   private final Object y;

   @NotNull
   public String toString() {
      return "" + this.x + '#' + this.y;
   }

   public final Object getX() {
      return this.x;
   }

   public final Object getY() {
      return this.y;
   }

   public B(Object x, Object y) {
      this.x = x;
      this.y = y;
   }

   public B(Object x) {
      this(x, x);
   }
}


//File Main.kt


fun box(): String {
    val b1 = B("1", "2").toString()
    if (b1 != "1#2") return "fail1: $b1"
    val b2 = B("abc").toString()
    if (b2 != "abc#abc") return "fail2: $b2"

    val a1 = A().toString()
    if (a1 != "default#default") return "fail3: $a1"
    val a2 = A("xyz").toString()
    if (a2 != "xyz#default") return "fail4: $a2"

    return "OK"
}

