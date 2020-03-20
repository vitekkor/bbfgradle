//File A.java
import kotlin.Metadata;

public interface A {
   int foo();
}


//File B1.java
import kotlin.Metadata;

public final class B1 implements A {
   public int foo() {
      return 10;
   }
}


//File Main.kt




fun f1(b: B1): Int {
    val o = object : A by b { }
    return o.foo()
}

fun f2(b: B2): Int {
    val o = object : A by B2(b.z) { }
    return o.foo()
}

fun f3(b: B2, mult: Int): Int {
    val o = object : A by B2(mult * b.z) { }
    return o.foo()
}

fun f4(b: B1, x: Int, y: Int, z: Int): Int {
    val o = object : A by b {
        fun bar() = x + y + z
    }
    return o.foo()
}


fun box(): String {
    if (f1(B1()) != 10) return "fail #1"
    if (f2(B2(239)) != 239) return "fail #2"
    if (f3(B2(239), 2) != 239*2) return "fail #3"
    if (f4(B1(), 1, 2, 3) != 10) return "fail #4"
    return "OK"
}



//File B2.java
import kotlin.Metadata;

public final class B2 implements A {
   private final int z;

   public int foo() {
      return this.z;
   }

   public final int getZ() {
      return this.z;
   }

   public B2(int z) {
      this.z = z;
   }
}
