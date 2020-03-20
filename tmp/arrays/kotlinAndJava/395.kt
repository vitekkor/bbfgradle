//File Father.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Father {
   @NotNull
   private final String param;

   @NotNull
   public final String getParam() {
      return this.param;
   }

   public Father(@NotNull String param) {
      super();
      this.param = param;
   }

   public abstract class InClass {
      @NotNull
      public final String work() {
         return Father.this.getParam();
      }
   }

   public final class Child extends Father {
      public Child(@NotNull String p) {
         super(p);
      }

      public final class Child2 extends Father.InClass {
         public Child2() {
            super();
         }
      }
   }
}


//File Main.kt


fun box(): String {
    return Father("fail").Child("OK").Child2().work()
}

