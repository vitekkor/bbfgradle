//File E.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public enum E {
   A,
   B;

   @NotNull
   public final String foo() {
      return this.name();
   }
}


//File Main.kt


fun box(): String {
    val f = E.A::foo
    val ef = E::foo

    if (f() != "A") return "Fail 1: ${f()}"
    if (f == E.B::foo) return "Fail 2"
    if (ef != E::foo) return "Fail 3"

    return "OK"
}

