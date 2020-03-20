//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String foo();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class B implements A {
   @NotNull
   public String foo() {
      return "OK";
   }
}


//File Main.kt


internal val global = B()

fun box(): String {
    return C().foo()
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C implements A {
   // $FF: synthetic field
   private final B $$delegate_0;

   public C(int x) {
      this.$$delegate_0 = MainKt.getGlobal();
   }

   public C() {
      this(1);
   }

   @NotNull
   public String foo() {
      return this.$$delegate_0.foo();
   }
}
