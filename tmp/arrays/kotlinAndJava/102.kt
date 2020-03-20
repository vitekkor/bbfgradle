//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String getFoo();
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B implements A {
   @NotNull
   private String foo = "Fail";

   @NotNull
   public String getFoo() {
      return this.foo;
   }

   public final void setOK(@NotNull B other) {
      other.foo = "OK";
   }
}


//File Main.kt


fun box(): String {
    val b = B()
    b.setOK(b)
    return b.foo
}

