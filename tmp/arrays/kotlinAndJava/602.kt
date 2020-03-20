//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final StringBuilder foo;

   @NotNull
   public final Outer.Inner test() {
      return new Outer.Inner();
   }

   @NotNull
   public final StringBuilder getFoo() {
      return this.foo;
   }

   public Outer(@NotNull StringBuilder foo) {
      super();
      this.foo = foo;
   }

   public final class Inner {
      public final int len() {
         return Outer.this.getFoo().length();
      }
   }
}


//File Main.kt


fun box() : String {
  val sb = StringBuilder("xyzzy")
  val o = Outer(sb)
  val i = o.test()
  val l = i.len()
  return if (l != 5) "fail" else "OK"
}

