//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A implements BK, KTrait {
   @NotNull
   public String foo() {
      return KTrait.DefaultImpls.foo(this);
   }
}


//File BK.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface BK {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(BK $this) {
         return String.valueOf(10);
      }
   }
}


//File Main.kt


fun box(): String {
    return if (A().foo() == "30") "OK" else "fail"
}



//File KTrait.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface KTrait extends BK {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(KTrait $this) {
         return String.valueOf(30);
      }
   }
}
