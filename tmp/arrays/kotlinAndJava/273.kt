//File TestClass.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TestClass {
   @Nullable
   private String xx;
   @NotNull
   private final String str;

   @Nullable
   public final String getXx() {
      return this.xx;
   }

   public final void setXx(@Nullable String var1) {
      this.xx = var1;
   }

   @NotNull
   public final String getStr() {
      return this.str;
   }

   public TestClass(@NotNull String str) {
      super();
      this.str = str;

      final class A {
         @NotNull
         private final String x = TestClass.this.getStr();

         @NotNull
         public final String getX() {
            return this.x;
         }

         public A() {
         }
      }

      this.xx = (new A()).getX();
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
fun testFun1(str: String): String {
    val capture = str

    class A {
        val x = capture
    }

    return A().x
}


fun testFun2(str: String): String {
    class A {
        val x = str
    }
    fun bar() = A()
    return bar().x
}


fun testFun3(str: String): String = TestClass(str).xx!!


fun String.testFun4(): String {
    class A {
        val x = this@testFun4
    }
    return A().x
}


fun box(): String {
    return when {
        testFun1("test1") != "test1" -> "Fail #1 (local class with capture)"
        testFun2("test2") != "test2" -> "Fail #2 (local class with capture ctor in another context)"
        testFun3("test3") != "test3" -> "Fail #3 (local class with capture ctor in init{ ... })"
        "test4".testFun4() != "test4" -> "Fail #4 (local class with extension receiver)"
        else -> "OK"
    }
}

