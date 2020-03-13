//File Foo.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Foo {
   @NotNull
   private final String s;

   public boolean equals(@Nullable Object other) {
      return other != null && Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(other.getClass()), Reflection.getOrCreateKotlinClass(this.getClass())) && Intrinsics.areEqual(this.s, ((Foo)other).s);
   }

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
// KT-16291 Smart cast doesn't work when getting class of instance

fun box(): String {
    return if (Foo("a") == Foo("a")) "OK" else "Fail"
}

