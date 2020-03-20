//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String getStr();

   public static final class DefaultImpls {
      @NotNull
      public static String getStr(A $this) {
         return "OK";
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B extends A {
   public static final class DefaultImpls {
      @NotNull
      public static String getStr(B $this) {
         return A.DefaultImpls.getStr((A)$this);
      }
   }
}


//File Impl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Impl implements B {
   @NotNull
   public String getStr() {
      return B.DefaultImpls.getStr(this);
   }
}


//File Main.kt


fun box() = Impl().str

