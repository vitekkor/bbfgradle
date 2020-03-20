//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Outer {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   public Outer(@NotNull String x) {
      super();
      this.x = x;
   }

   public class Inner {
   }

   public final class JavacBug extends Base {
      public JavacBug() {
         super((Test)(new Test() {
            @NotNull
            public String test() {
               return Outer.this.getX();
            }
         }));
      }
   }
}


//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Test {
   @NotNull
   String test();
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base {
   @NotNull
   private final Test test;

   @NotNull
   public final Test getTest() {
      return this.test;
   }

   public Base(@NotNull Test test) {
      super();
      this.test = test;
   }
}


//File Main.kt


fun box() = Outer("OK").JavacBug().test.test()

