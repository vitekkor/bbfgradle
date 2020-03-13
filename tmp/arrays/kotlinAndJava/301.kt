//File X.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class X {
   private final String n;

   @NotNull
   public final String foo() {
      return (new X("inner") {
         @NotNull
         public final String print() {
            return X.this.n;
         }
      }).print();
   }

   public X(@NotNull String n) {
      super();
      this.n = n;
   }
}


//File Main.kt



fun box() : String {
  return X("OK").foo()
}

