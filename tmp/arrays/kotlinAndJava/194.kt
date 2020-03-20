//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String result = "Fail";

   @NotNull
   public final String getResult() {
      return this.result;
   }

   public final void setResult(@NotNull String var1) {
      this.result = var1;
   }

   private final String getFoo(int $this$foo) {
      return this.result;
   }

   private final void setFoo(int $this$foo, String value) {
      this.result = value;
   }

   @NotNull
   public final String run() {
      final class O {
         public final void run() {
            A.this.setFoo(42, "OK");
         }

         public O() {
         }
      }

      (new O()).run();
      return this.getFoo(-42);
   }

   // $FF: synthetic method
   public static final String access$getFoo$p(A $this, int $this$access_u24foo_u24p) {
      return $this.getFoo($this$access_u24foo_u24p);
   }
}


//File Main.kt


fun box() = A().run()

