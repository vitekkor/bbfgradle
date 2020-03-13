//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   /** @deprecated */
   @Deprecated
   A.Companion Companion = A.Companion.$$INSTANCE;

   @NotNull
   String test();

   public static final class DefaultImpls {
      @NotNull
      public static String test(A $this) {
         return A.Companion.ok();
      }
   }

   public static final class Companion {
      // $FF: synthetic field
      static final A.Companion $$INSTANCE;

      @NotNull
      public final String ok() {
         return "OK";
      }

      private Companion() {
      }

      static {
         A.Companion var0 = new A.Companion();
         $$INSTANCE = var0;
      }
   }
}


//File Main.kt
// !LANGUAGE: -ProperVisibilityForCompanionObjectInstanceField

fun box() = C().test()



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C implements A {
   @NotNull
   public String test() {
      return A.DefaultImpls.test(this);
   }
}
