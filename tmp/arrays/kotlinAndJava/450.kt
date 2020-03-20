//File Main.kt


fun box(): String {
    val c = C(10, 3)
    return if (c.child.toString() == "child13" && c.child.toString() == "child16" && c.child.toString() == "child19") "OK" else "fail"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.IntRef;
import org.jetbrains.annotations.NotNull;

public final class C {
   @NotNull
   private final Object child;
   private final int y;

   @NotNull
   public final Object initChild(int x0) {
      final IntRef x = new IntRef();
      x.element = x0;
      return new Object() {
         @NotNull
         public String toString() {
            x.element += C.this.getY();
            return "child" + x.element;
         }
      };
   }

   @NotNull
   public final Object getChild() {
      return this.child;
   }

   public final int getY() {
      return this.y;
   }

   public C(int x, int y) {
      this.y = y;
      this.child = this.initChild(x);
   }
}
