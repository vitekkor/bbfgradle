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
      @NotNull
      public final Father.InClass test() {
         final class Local extends Father.InClass {
            public Local() {
               super();
            }
         }

         return (Father.InClass)(new Local());
      }

      public Child(@NotNull String p) {
         super(p);
      }
   }
}


//File Main.kt


fun box(): String {
    return Father("fail").Child("OK").test().work()
}

