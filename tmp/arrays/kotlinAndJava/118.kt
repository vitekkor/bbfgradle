//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Test {
   @NotNull
   private String storage = "Fail";

   @NotNull
   public final String getStorage() {
      return this.storage;
   }

   public final void setStorage(@NotNull String var1) {
      this.storage = var1;
   }

   @NotNull
   public final String getFoo(int $this$foo) {
      return this.storage;
   }

   public final void setFoo(int $this$foo, @NotNull String value) {
      this.storage = value;
   }

   @NotNull
   public final String test() {
      int i = 1;
      this.setFoo(i, "OK");
      return this.getFoo(i);
   }
}


//File Main.kt


fun box(): String {
    return Test().test()
}

