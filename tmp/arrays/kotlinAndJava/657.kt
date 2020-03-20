//File A.java
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String publishedProp = "K";

   @PublishedApi
   @NotNull
   public final String published() {
      return "O";
   }

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void publishedProp$annotations() {
   }

   @NotNull
   public final String getPublishedProp() {
      return this.publishedProp;
   }

   public final void setPublishedProp(@NotNull String var1) {
      this.publishedProp = var1;
   }

   @NotNull
   public final String test() {
      int $i$f$test = 0;
      return this.published() + this.getPublishedProp();
   }
}


//File Main.kt


fun box() : String {
    val clazz = A::class.java
    if (clazz.getDeclaredMethod("published") == null) return "fail 1"
    if (clazz.getDeclaredMethod("getPublishedProp") == null) return "fail 2"
    if (clazz.getDeclaredMethod("setPublishedProp", String::class.java) == null) return "fail 3"
    return A().test()
}

