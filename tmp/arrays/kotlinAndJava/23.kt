//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;

public final class B {
   @NotNull
   private String x;

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

   @NotNull
   public final String foo2() {
      final class Y extends C {
         public Y() {
            super(B.this.getX());
         }
      }

      return (new Y()).test();
   }

   @NotNull
   public final String getX() {
      return this.x;
   }

   public final void setX(@NotNull String var1) {
      this.x = var1;
   }

   public B(@NotNull String x) {
      super();
      this.x = x;
   }
}


//File Main.kt



fun box(): String {
    val b = B("OK")
    if (b.foo() != "OK") return "fail: ${b.foo()}"
    return b.foo2()
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
