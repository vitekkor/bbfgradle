//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A extends K {
   @NotNull
   private final String foo = "A.foo";

   @NotNull
   public String getFoo() {
      return this.foo;
   }

   @NotNull
   public String bar() {
      return "A.bar";
   }

   public final class B extends K {
      @NotNull
      private final String foo = "B.foo";

      @NotNull
      public String getFoo() {
         return this.foo;
      }

      @NotNull
      public String bar() {
         return "B.bar";
      }

      @NotNull
      public final String test1() {
         return A.super.getFoo();
      }

      @NotNull
      public final String test2() {
         return super.getFoo();
      }

      @NotNull
      public final String test3() {
         return super.getFoo();
      }

      @NotNull
      public final String test4() {
         return A.super.getFoo();
      }

      @NotNull
      public final String test5() {
         return super.getFoo();
      }

      @NotNull
      public final String test6() {
         return super.getFoo();
      }
   }
}


//File Base.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Base {
   @NotNull
   String getFoo();

   @NotNull
   String bar();
}


//File K.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class K implements Base {
   @NotNull
   private final String foo = this.bar();

   @NotNull
   public String getFoo() {
      return this.foo;
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

