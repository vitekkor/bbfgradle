//File A.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String prop = "OK";

   @NotNull
   public final String getProp() {
      return this.prop;
   }

   @NotNull
   public final String test() {
      return (String)((Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return A.this.getProp();
         }
      })).invoke();
   }

   // $FF: synthetic method
   public static final String access$getProp$p(A $this) {
      return $this.prop;
   }

   // $FF: synthetic method
   public static final void access$setProp$p(A $this, String var1) {
      $this.prop = var1;
   }
}


//File Main.kt


fun box(): String = A().test()

