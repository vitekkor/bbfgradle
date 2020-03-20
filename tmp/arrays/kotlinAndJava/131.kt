//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface A {
   @Nullable
   Object foo();

   @NotNull
   String bar();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B {
   @NotNull
   String foo();
}


//File Main.kt


fun <T> bar(x: T): String where T : A, T : B {
    if (x.foo().length != 2 || x.foo() != "OK") return "fail 1"
    if (x.bar() != "ok") return "fail 2"

    return "OK"
}

fun box(): String = bar(C())



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C implements A, B {
   @NotNull
   public String foo() {
      return "OK";
   }

   @NotNull
   public String bar() {
      return "ok";
   }
}
