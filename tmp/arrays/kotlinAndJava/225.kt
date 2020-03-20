//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String f1();

   @NotNull
   String f2();

   @NotNull
   String f3();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

import kotlin.test.assertEquals

fun doWork(block: () -> String) = block()
inline fun doWorkInline(block: () -> String) = block()

fun box(): String {
    val x = object {
        inline fun <reified T : Any> bar1(): A = object : A {
            override fun f1(): String = T::class.java.getName()
            override fun f2(): String = doWork { T::class.java.getName() }
            override fun f3(): String = doWorkInline { T::class.java.getName() }
        }

        inline fun <reified T : Any> bar2() = T::class.java.getName()
        inline fun <reified T : Any> bar3() = doWork { T::class.java.getName() }
        inline fun <reified T : Any> bar4() = doWorkInline { T::class.java.getName() }
    }

    val y: A = x.bar1<String>()
    assertEquals("java.lang.String", y.f1())
    assertEquals("java.lang.String", y.f2())
    assertEquals("java.lang.String", y.f3())


    assertEquals("java.lang.Integer", x.bar2<Int>())
    assertEquals("java.lang.Double", x.bar3<Double>())
    assertEquals("java.lang.Long", x.bar4<Long>())

    return "OK"
}

