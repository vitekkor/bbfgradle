//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Test {
   @NotNull
   private String doubleStorage = "fail";
   @NotNull
   private String longStorage = "fail";

   @NotNull
   public final String getDoubleStorage() {
      return this.doubleStorage;
   }

   public final void setDoubleStorage(@NotNull String var1) {
      this.doubleStorage = var1;
   }

   @NotNull
   public final String getLongStorage() {
      return this.longStorage;
   }

   public final void setLongStorage(@NotNull String var1) {
      this.longStorage = var1;
   }

   @NotNull
   public final String getFoo(double $this$foo) {
      return this.doubleStorage;
   }

   public final void setFoo(double $this$foo, @NotNull String value) {
      this.doubleStorage = value;
   }

   @NotNull
   public final String getBar(long $this$bar) {
      return this.longStorage;
   }

   public final void setBar(long $this$bar, @NotNull String value) {
      this.longStorage = value;
   }

   @NotNull
   public final String test() {
      double d = 1.0D;
      this.setFoo(d, "O");
      long l = 1L;
      this.setBar(l, "K");
      return this.getFoo(d) + this.getBar(l);
   }
}


//File Main.kt


fun box(): String {
    return Test().test()
}

