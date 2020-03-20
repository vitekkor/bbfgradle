//File I1.java
import kotlin.Metadata;

public interface I1 {
}


//File K.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface K {
   void c1(@NotNull C var1);

   void c2(@NotNull C var1);

   void c3(@NotNull C var1);

   void c4(@NotNull C var1);

   void i1(@NotNull I1 var1);

   void i2(@NotNull I2 var1);
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val k = K::class.java

    k.getDeclaredMethod("c1", C::class.java)
    k.getDeclaredMethod("c2", C::class.java)
    k.getDeclaredMethod("c3", C::class.java)
    k.getDeclaredMethod("c4", C::class.java)

    k.getDeclaredMethod("i1", I1::class.java)
    k.getDeclaredMethod("i2", I2::class.java)

    return "OK"
}



//File C.java
import kotlin.Metadata;

public class C {
}


//File I2.java
import kotlin.Metadata;

public interface I2 {
}
