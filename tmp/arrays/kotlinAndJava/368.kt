//File Abstract.java
import kotlin.Metadata;

public interface Abstract {
}


//File X.java
import kotlin.Metadata;

public class X {
   private final int x;

   public final int getX() {
      return this.x;
   }

   public X(int x) {
      this.x = x;
   }
}


//File Point.java
import kotlin.Metadata;

public final class Point extends X implements Y {
   private final int y;

   public int getY() {
      return this.y;
   }

   public Point(int x, int yy) {
      super(x);
      this.y = yy;
   }
}


//File P1.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class P1 extends X implements Abstract, Y {
   // $FF: synthetic field
   private final Y $$delegate_0;

   public P1(int x, @NotNull Y yy) {
      super(x);
      this.$$delegate_0 = yy;
   }

   public int getY() {
      return this.$$delegate_0.getY();
   }
}


//File P4.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class P4 extends X implements Y, Abstract {
   // $FF: synthetic field
   private final Y $$delegate_0;

   public P4(int x, @NotNull Y yy) {
      super(x);
      this.$$delegate_0 = yy;
   }

   public int getY() {
      return this.$$delegate_0.getY();
   }
}


//File Y.java
import kotlin.Metadata;

public interface Y {
   int getY();
}


//File P2.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class P2 extends X implements Abstract, Y {
   // $FF: synthetic field
   private final Y $$delegate_0;

   public P2(int x, @NotNull Y yy) {
      super(x);
      this.$$delegate_0 = yy;
   }

   public int getY() {
      return this.$$delegate_0.getY();
   }
}


//File YImpl.java
import kotlin.Metadata;

public final class YImpl implements Y {
   private final int y;

   public int getY() {
      return this.y;
   }

   public YImpl(int y) {
      this.y = y;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// Changed when traits were introduced. May not make sense any more

fun box() : String {
    if (X(239).x != 239) return "FAIL #1"
    if (YImpl(239).y != 239) return "FAIL #2"

    val p = Point(240, -1)
    if (p.x + p.y != 239) return "FAIL #3"

    val y = YImpl(-1)
    val p1 = P1(240, y)
    if (p1.x + p1.y != 239) return "FAIL #4"
    val p2 = P2(240, y)
    if (p2.x + p2.y != 239) return "FAIL #5"

    val p3 = P3(240, y)
    if (p3.x + p3.y != 239) return "FAIL #6"

    val p4 = P4(240, y)
    if (p4.x + p4.y != 239) return "FAIL #7"

    return "OK"
}



//File P3.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class P3 extends X implements Y, Abstract {
   // $FF: synthetic field
   private final Y $$delegate_0;

   public P3(int x, @NotNull Y yy) {
      super(x);
      this.$$delegate_0 = yy;
   }

   public int getY() {
      return this.$$delegate_0.getY();
   }
}
