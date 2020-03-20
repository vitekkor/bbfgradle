//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final Outer.Inner x = new Outer.Inner();

   @NotNull
   public final Outer.Inner getX() {
      return this.x;
   }

   public final class Inner {
      @NotNull
      public final Outer getOuter() {
         return Outer.this;
      }
   }
}


//File Main.kt


fun box() : String {
  val o = Outer()
  return if (o === o.x.outer) "OK" else "fail"
}

