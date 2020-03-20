//File T.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class T {
   @NotNull
   private final Function0 f;

   @Nullable
   public final Object call() {
      return this.f.invoke();
   }

   @NotNull
   public final Function0 getF() {
      return this.f;
   }

   public T(@NotNull Function0 f) {
      super();
      this.f = f;
   }
}


//File Main.kt

fun box(): String {
    return T({ "OK" }).call() as String
}

