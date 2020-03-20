//File Second.java
import kotlin.Metadata;

public interface Second extends First {
   int bar();
}


//File Impl.java
import kotlin.Metadata;

public final class Impl implements Second {
   public int foo() {
      return 1;
   }

   public int bar() {
      return 2;
   }
}


//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Test implements Second {
   // $FF: synthetic field
   private final Second $$delegate_0;

   public Test(@NotNull Second s) {
      super();
      this.$$delegate_0 = s;
   }

   public int bar() {
      return this.$$delegate_0.bar();
   }

   public int foo() {
      return this.$$delegate_0.foo();
   }
}


//File Main.kt


fun box() : String {
    var t = Test(Impl())
    if (t.foo() != 1)
        return "Fail #1"
    if (t.bar() != 2)
        return "Fail #2"
    if (t !is First)
        return "Fail #3"
    if (t !is Second)
        return "Fail #4"

    return "OK"
}



//File First.java
import kotlin.Metadata;

public interface First {
   int foo();
}
