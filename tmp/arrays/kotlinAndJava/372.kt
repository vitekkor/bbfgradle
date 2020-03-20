//File Delegate.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Delegate implements Base {
   @NotNull
   public String test() {
      return "OK";
   }
}


//File Base2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Base2 extends Base {
   @NotNull
   String test();

   public static final class DefaultImpls {
      @NotNull
      public static String test(Base2 $this) {
         return "base 2fail";
      }
   }
}


//File Base.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Base {
   @NotNull
   String test();

   public static final class DefaultImpls {
      @NotNull
      public static String test(Base $this) {
         return "base fail";
      }
   }
}


//File Main.kt


fun box(): String {
    return object : Base2, Base by Delegate() {
    }.test()
}

