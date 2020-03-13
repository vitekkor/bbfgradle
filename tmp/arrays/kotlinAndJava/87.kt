//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   public final void foo(@NotNull Outer.Inner $this$foo) {
      new Outer();
      new Outer.Nested();
      new Outer.Inner();
   }

   public final void bar(@NotNull Outer.Nested $this$bar) {
      new Outer();
      new Outer.Nested();
      new Outer.Inner();
   }

   public final void baz(@NotNull Outer $this$baz) {
      new Outer();
      new Outer.Nested();
      $this$baz.new Inner();
   }

   @NotNull
   public final String box() {
      this.foo(new Outer.Inner());
      this.bar(new Outer.Nested());
      this.baz(this);
      return "OK";
   }

   public static final class Nested {
   }

   public final class Inner {
   }
}


//File Main.kt


fun box() = Outer().box()

