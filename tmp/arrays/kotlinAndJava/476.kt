//File Foo.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Foo {
   @NotNull
   private final Foo.Inner f;
   private final String s;

   @NotNull
   public final Foo.Inner getF() {
      return this.f;
   }

   public Foo(@NotNull String s) {
      super();
      this.s = s;
      this.f = new Foo.Inner();
   }

   public final class Inner {
      private final String x = (String)((Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return Foo.this.s;
         }
      })).invoke();
   }
}


//File Main.kt


fun box(): String {
    Foo("!")
    return "OK"
}

