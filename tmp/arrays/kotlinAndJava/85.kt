//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class Outer {
   @Nullable
   private final Outer.InnerBase foo = (Outer.InnerBase)(new Outer.InnerDerived());

   @Nullable
   public final Outer.InnerBase getFoo() {
      return this.foo;
   }

   public class InnerBase {
   }

   public final class InnerDerived extends Outer.InnerBase {
      public InnerDerived() {
         super();
      }
   }
}


//File Main.kt


fun box() : String {
  val o = Outer()
  return if (o.foo === null) "fail" else "OK"
}

