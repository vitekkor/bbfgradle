//File OneImpl.java
import kotlin.Metadata;

public final class OneImpl implements One {
   public int foo() {
      return 1;
   }

   public int faz() {
      return One.DefaultImpls.faz(this);
   }
}


//File Test2.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Test2 implements Two, One {
   // $FF: synthetic field
   private final Two $$delegate_0;
   // $FF: synthetic field
   private final One $$delegate_1;

   public int foo() {
      return 0;
   }

   public Test2(@NotNull One a, @NotNull Two b) {
      super();
      this.$$delegate_0 = b;
      this.$$delegate_1 = a;
   }

   public int quux() {
      return this.$$delegate_0.quux();
   }

   public int faz() {
      return this.$$delegate_1.faz();
   }
}


//File TwoImpl.java
import kotlin.Metadata;

public final class TwoImpl implements Two {
   public int foo() {
      return 2;
   }

   public int quux() {
      return Two.DefaultImpls.quux(this);
   }
}


//File One.java
import kotlin.Metadata;

public interface One {
   int foo();

   int faz();

   public static final class DefaultImpls {
      public static int faz(One $this) {
         return 10;
      }
   }
}


//File Main.kt


fun box() : String {
    var t2 = Test2(OneImpl(), TwoImpl())
    if (t2.foo() != 0)
        return "Fail #1"
    if (t2.faz() != 10)
        return "Fail #2"
    if (t2.quux() != 100)
        return "Fail #3"
    if (t2 !is One)
        return "Fail #4"
    if (t2 !is Two)
        return "Fail #5"

    return "OK"
}



//File Two.java
import kotlin.Metadata;

public interface Two {
   int foo();

   int quux();

   public static final class DefaultImpls {
      public static int quux(Two $this) {
         return 100;
      }
   }
}
