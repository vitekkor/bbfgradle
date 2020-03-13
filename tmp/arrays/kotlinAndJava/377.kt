//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class B implements MyTrait {
   @NotNull
   private String property;

   @NotNull
   public String getProperty() {
      return this.property;
   }

   public void setProperty(@NotNull String var1) {
      this.property = var1;
   }

   @NotNull
   public String foo() {
      return MyTrait.DefaultImpls.foo(this);
   }

   public B(@NotNull String param) {
      super();
      this.property = param;
   }
}


//File Main.kt


fun box()= B("OK").foo()



//File MyTrait.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface MyTrait {
   @NotNull
   String getProperty();

   void setProperty(@NotNull String var1);

   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(MyTrait $this) {
         return $this.getProperty();
      }
   }
}
