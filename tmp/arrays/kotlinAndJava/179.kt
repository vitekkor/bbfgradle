//File Abstract.java
import kotlin.Metadata;

public interface Abstract {
}


//File Derived2.java
import kotlin.Metadata;

public final class Derived2 extends Base implements Abstract {
}


//File Derived1.java
import kotlin.Metadata;

public final class Derived1 extends Base implements Abstract {
}


//File Base.java
import kotlin.Metadata;

public class Base {
   private final int plain = 239;
   private int readwrite;

   public final int getPlain() {
      return this.plain;
   }

   public final int getRead() {
      return 239;
   }

   public final int getReadwrite() {
      return this.readwrite + 1;
   }

   public final void setReadwrite(int n) {
      this.readwrite = n;
   }
}


//File Main.kt


fun code(s : Base) : Int {
    if (s.plain != 239) return 1
    if (s.read != 239) return 2
    s.readwrite = 238
    if (s.readwrite != 239) return 3
    return 0
}

fun test(s : Base) : Boolean = code(s) == 0

fun box() : String {
    if (!test(Base())) return "Fail #1"
    if (!test(Derived1())) return "Fail #2"
    if (!test(Derived2())) return "Fail #3"
    return "OK"
}

