//File T.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T {
   @NotNull
   String result();
}


//File A.java
import kotlin.Metadata;

public abstract class A {
   private final Object x;

   public final Object getX() {
      return this.x;
   }

   public A(Object x) {
      this.x = x;
   }
}


//File B.java
import kotlin.Metadata;

public class B extends A {
   public B() {
      super("OK");
   }
}


//File Main.kt


fun box() = C().foo().result()



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B {
   @NotNull
   public final T foo() {
      return (T)(new T() {
         @NotNull
         private final String bar = (String)C.this.getX();

         @NotNull
         public final String getBar() {
            return this.bar;
         }

         @NotNull
         public String result() {
            return this.bar;
         }
      });
   }
}
