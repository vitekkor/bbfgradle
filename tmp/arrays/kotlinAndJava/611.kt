//File B.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B {
   @NotNull
   private final String x;

   @NotNull
   public final C foo() {
      final class A extends C {
         public A() {
            super((Function0)(new Function0() {
               @NotNull
               public final String invoke() {
                  return B.this.getX();
               }
            }));
         }
      }

      return (C)(new A());
   }

   @NotNull
   public final String getX() {
      return this.x;
   }

   public B(@NotNull String x) {
      super();
      this.x = x;
   }
}


//File Main.kt


fun box() = B("OK").foo().f()



//File C.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class C {
   @NotNull
   private final Function0 f;

   @NotNull
   public final Function0 getF() {
      return this.f;
   }

   public C(@NotNull Function0 f) {
      super();
      this.f = f;
   }
}
