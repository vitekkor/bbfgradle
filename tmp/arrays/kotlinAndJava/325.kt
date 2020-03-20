//File Foo.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Foo {
   @NotNull
   private final String s;

   @NotNull
   public final String getS() {
      return this.s;
   }

   public Foo(@NotNull String s) {
      super();
      this.s = s;
   }
}


//File Main.kt
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

fun foo(): Foo? = Foo("OK")

fun <T> run(f: () -> T): T = f()

val foo: Foo = run {
    val x = foo()
    if (x == null) throw Exception()
    x
}

fun box() = foo.s

