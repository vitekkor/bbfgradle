//File Bar.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Bar {
   @NotNull
   private final String name;

   @NotNull
   public final String getName() {
      return this.name;
   }

   public Bar(@NotNull String name) {
      super();
      this.name = name;
   }
}


//File Foo.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class Foo {
   @NotNull
   public abstract String foo();
}


//File Main.kt


fun box(): String {
    return object: Foo() {
      inner class NestedFoo(val bar: Bar) {
          fun copy(bar: Bar) = NestedFoo(bar)
      }

      override fun foo(): String {
        return NestedFoo(Bar("Fail")).copy(bar = Bar("OK")).bar.name
      }
    }.foo()
}

