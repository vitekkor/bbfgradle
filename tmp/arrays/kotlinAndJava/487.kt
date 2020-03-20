//File Foo.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class Foo {
   private int rnd = 10;

   public final int getRnd() {
      return this.rnd;
   }

   public final void setRnd(int var1) {
      this.rnd = var1;
   }

   public boolean equals(@Nullable Object that) {
      return that instanceof Foo && ((Foo)that).rnd == this.rnd;
   }
}


//File Main.kt


fun box() : String {
  val a = Foo()
  val b = Foo()
  if (a !== a) return "fail 1"
  if (b !== b) return "fail 2"
  if (b === a) return "fail 3"
  if (a === b) return "fail 4"
  if( a !=b ) return "fail5"
  return "OK"
}

