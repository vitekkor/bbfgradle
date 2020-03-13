//File TestClass.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class TestClass implements Test {
   @NotNull
   public String test() {
      return Test.DefaultImpls.test(this);
   }
}


//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Test {
   @NotNull
   String test();

   public static final class DefaultImpls {
      @NotNull
      public static String test(Test $this) {
         return "OK";
      }
   }
}


//File Main.kt
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8

fun box(): String {
    return TestClass().test()
}

