//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Test {
   private final int content = 1;

   public final int getContent() {
      return this.content;
   }

   public final class A {
      @NotNull
      private final Object v = new Object() {
         public final int f() {
            return Test.this.getContent();
         }
      };

      @NotNull
      public final Object getV() {
         return this.v;
      }
   }
}


//File Main.kt


fun box(): String {
    Test().A()

    return "OK"
}

