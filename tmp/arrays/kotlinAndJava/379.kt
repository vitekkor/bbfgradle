//File D.java
import kotlin.Metadata;

public class D extends C {
}


//File A.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   public final int invoke(@NotNull Function1 f) {
      return 1;
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B {
   public final int invoke(@NotNull Function1 f) {
      return 2;
   }
}


//File Main.kt
//KT-3772 Invoke and overload resolution ambiguity

val C.attr: A get() = A()

val D.attr: B get() = B()


fun box(): String {
    val d = D()
    return if (d.attr {} == 2) "OK" else "fail"
}



//File C.java
import kotlin.Metadata;

public class C {
}
