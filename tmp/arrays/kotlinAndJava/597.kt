//File Expr.java
import kotlin.Metadata;

public interface Expr {
   int ttFun();

   public static final class DefaultImpls {
      public static int ttFun(Expr $this) {
         return 12;
      }
   }
}


//File Num.java
import kotlin.Metadata;

public final class Num implements Expr {
   private final int value;

   public final int getValue() {
      return this.value;
   }

   public Num(int value) {
      this.value = value;
   }

   public int ttFun() {
      return Expr.DefaultImpls.ttFun(this);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun Expr.sometest() : Int {
    if (this is Num) {
        value
        return value
    }
    return 0;
}


fun box() : String {
    if (Num(11).sometest() != 11) return "fail ${Num(11).sometest()}"

    return "OK"
}

