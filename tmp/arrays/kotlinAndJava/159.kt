//File Trait2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Trait2 {
   @NotNull
   String bar();
}


//File T1.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class T1 implements Trait1 {
   @NotNull
   public String foo() {
      return "aaa";
   }
}


//File Trait1.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Trait1 {
   @NotNull
   String foo();
}


//File T2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class T2 implements Trait2 {
   @NotNull
   public String bar() {
      return "bbb";
   }
}


//File Main.kt


fun box() : String {
    val c = C(T1(),T2())
    if(c.foo() != "aaa") return "fail"
    if(c.bar() != "bbb") return "fail"
    return "OK"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C implements Trait1, Trait2 {
   // $FF: synthetic field
   private final Trait1 $$delegate_0;
   // $FF: synthetic field
   private final Trait2 $$delegate_1;

   public C(@NotNull Trait1 a, @NotNull Trait2 b) {
      super();
      this.$$delegate_0 = a;
      this.$$delegate_1 = b;
   }

   @NotNull
   public String foo() {
      return this.$$delegate_0.foo();
   }

   @NotNull
   public String bar() {
      return this.$$delegate_1.bar();
   }
}
