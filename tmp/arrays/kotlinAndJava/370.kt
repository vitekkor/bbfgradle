//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A implements K {
   @NotNull
   public String foo() {
      return "A.foo";
   }

   @NotNull
   public String bar() {
      return "A.bar";
   }

   public final class B implements K {
      @NotNull
      public String foo() {
         return "B.foo";
      }

      @NotNull
      public String bar() {
         return "B.bar";
      }

      @NotNull
      public final String test1() {
         return K.DefaultImpls.foo(A.this);
      }

      @NotNull
      public final String test2() {
         return K.DefaultImpls.foo(this);
      }

      @NotNull
      public final String test3() {
         return K.DefaultImpls.foo(this);
      }

      @NotNull
      public final String test4() {
         return K.DefaultImpls.foo(A.this);
      }

      @NotNull
      public final String test5() {
         return K.DefaultImpls.foo(this);
      }

      @NotNull
      public final String test6() {
         return K.DefaultImpls.foo(this);
      }
   }
}


//File BK.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface BK {
   @NotNull
   String foo();

   @NotNull
   String bar();
}


//File K.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface K extends BK {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(K $this) {
         return $this.bar();
      }
   }
}


//File Main.kt



fun box(): String {
    val b = A().B()
    if (b.test1() != "A.bar") return "test1 ${b.test1()}"
    if (b.test2() != "B.bar") return "test2 ${b.test2()}"
    if (b.test3() != "B.bar") return "test3 ${b.test3()}"
    if (b.test4() != "A.bar") return "test4 ${b.test4()}"
    if (b.test5() != "B.bar") return "test5 ${b.test5()}"
    if (b.test6() != "B.bar") return "test6 ${b.test6()}"
    
    return "OK"
}

