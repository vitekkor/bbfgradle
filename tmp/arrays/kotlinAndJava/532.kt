//File ComplexClass.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class ComplexClass implements Trait {
   // $FF: synthetic field
   private final SimpleClass $$delegate_0 = new SimpleClass();

   @NotNull
   public final String qux() {
      return this.foo() + this.bar();
   }

   @NotNull
   public String bar() {
      return this.$$delegate_0.bar();
   }

   @NotNull
   public String foo() {
      return this.$$delegate_0.foo();
   }
}


//File Trait.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Trait {
   @NotNull
   String foo();

   @NotNull
   String bar();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(Trait $this) {
         return "O";
      }
   }
}


//File Main.kt


fun box() = ComplexClass().qux()



//File SimpleClass.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class SimpleClass implements Trait {
   @NotNull
   public String bar() {
      return "K";
   }

   @NotNull
   public String foo() {
      return Trait.DefaultImpls.foo(this);
   }
}
