//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String test();
}


//File MyClass.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class MyClass implements C {
   @NotNull
   private final C value;

   @NotNull
   public final C getValue() {
      return this.value;
   }

   public MyClass(@NotNull C value) {
      super();
      this.value = value;
   }

   @NotNull
   public String test() {
      return this.value.test();
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B {
   @NotNull
   String test();
}


//File Z.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Z implements C {
   @NotNull
   private final String param;

   @NotNull
   public String test() {
      return this.param;
   }

   @NotNull
   public final String getParam() {
      return this.param;
   }

   public Z(@NotNull String param) {
      super();
      this.param = param;
   }
}


//File Main.kt


fun box(): String {
    val s = MyClass(Z("OK"))
    return s.test()
}



//File C.java
import kotlin.Metadata;

public interface C extends A, B {
}
