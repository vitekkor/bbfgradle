//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;

public final class B {
   @NotNull
   public final String foo() {
      final ObjectRef s = new ObjectRef();
      s.element = "OK";

      final class Z extends C {
         public Z() {
            super((String)s.element);
         }
      }

      return (new Z()).test();
   }
}


//File Main.kt



fun box() : String {
    val b = B()
    val result = b.foo()
    return result
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class C {
   @NotNull
   private final String s;

   @NotNull
   public final String test() {
      return this.s;
   }

   @NotNull
   public final String getS() {
      return this.s;
   }

   public C(@NotNull String s) {
      super();
      this.s = s;
   }
}
