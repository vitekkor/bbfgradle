//File A.java
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   Collection foo();
}


//File B.java
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public abstract class B implements A {
   @NotNull
   public Collection foo() {
      return (Collection)null;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_REFLECT

fun box(): String {
    val clazz = B::class.java
    if (clazz.declaredMethods.first().genericReturnType.toString() != "java.util.Collection<java.lang.String>") return "fail 1"

    if (clazz.methods.filter { it.name == "foo" }.size != 1) return "fail 2"

    return "OK"
}

