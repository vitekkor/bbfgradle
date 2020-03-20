//File A.java
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

public final class A {
   @JvmField
   @NotNull
   public final B b = new B();
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

public final class B {
   @JvmField
   @NotNull
   public final C c = new C();
   @JvmField
   @NotNull
   public final String result = "OK";
}


//File Main.kt
// TARGET_BACKEND: JVM
// WITH_RUNTIME

fun box(): String {
    val a = A()
    a.b.c.d = a.b.result
    return a.b.c.d
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

public final class C {
   @JvmField
   @NotNull
   public String d = "Fail";
}
