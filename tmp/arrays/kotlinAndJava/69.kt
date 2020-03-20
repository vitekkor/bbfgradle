//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String a = "OK";

   @NotNull
   public String getA() {
      return this.a;
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   @NotNull
   private final String a = "FAIL";

   @NotNull
   public String getA() {
      return this.a;
   }

   @NotNull
   public final String foo() {
      return "CRUSH";
   }
}


//File Main.kt


fun box() = C().bar()



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class C {
   @NotNull
   public final String complex(@Nullable A $this$complex) {
      if ($this$complex instanceof B) {
         return ((B)$this$complex).foo();
      } else {
         return $this$complex != null ? $this$complex.getA() : "???";
      }
   }

   @NotNull
   public final String bar() {
      return this.complex(new A());
   }
}
