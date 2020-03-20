//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String s = "xyzzy";
   @NotNull
   private final Outer.InnerDerived x = new Outer.InnerDerived();

   @NotNull
   public final String getS() {
      return this.s;
   }

   @NotNull
   public final Outer.InnerDerived getX() {
      return this.x;
   }

   public class InnerBase {
      @NotNull
      private final String name;

      @NotNull
      public final String getName() {
         return this.name;
      }

      public InnerBase(@NotNull String name) {
         super();
         this.name = name;
      }
   }

   public final class InnerDerived extends Outer.InnerBase {
      public InnerDerived() {
         super(Outer.this.getS());
      }
   }
}


//File Main.kt


fun box() : String {
  val o = Outer()
  return if (o.x.name != "xyzzy") "fail" else "OK"
}

