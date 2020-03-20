//File A.java
import kotlin.Metadata;

public final class A {
   public static final class B {
      public static final class C {
      }
   }
}


//File Main.kt
import A.B
import A.B.C

fun box(): String {
    val a = A()
    val b = B()
    val ab = A.B()
    val c = C()
    val bc = B.C()
    val abc = A.B.C()
    return "OK"
}

