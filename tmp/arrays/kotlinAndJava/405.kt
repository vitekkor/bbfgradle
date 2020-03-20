//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String s = "OK";

   @NotNull
   public final String getS() {
      return this.s;
   }

   public final class Inner extends Foo {
      public Inner() {
         super((Function0)(new Function0() {
            @NotNull
            public final String invoke() {
               return Outer.this.getS();
            }
         }));
      }
   }
}


//File Foo.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Foo {
   @NotNull
   private final Function0 x;

   @NotNull
   public final Function0 getX() {
      return this.x;
   }

   public Foo(@NotNull Function0 x) {
      super();
      this.x = x;
   }
}


//File Main.kt


fun box() = Outer().Inner().x()

