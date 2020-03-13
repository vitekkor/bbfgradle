//File MyClass.java
import kotlin.Metadata;

@Ann(
   p1 = 128,
   p2 = 32768,
   p3 = Integer.MIN_VALUE,
   p4 = Integer.MIN_VALUE,
   p5 = 2147483648L,
   p6 = Long.MIN_VALUE
)
public final class MyClass {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val annotation = MyClass::class.java.getAnnotation(Ann::class.java)!!
    if (annotation.p1 != 128) return "fail 1, expected = ${128}, actual = ${annotation.p1}"
    if (annotation.p2 != 32768) return "fail 2, expected = ${32768}, actual = ${annotation.p2}"
    if (annotation.p3 != -2147483648) return "fail 3, expected = ${-2147483648}, actual = ${annotation.p3}"
    if (annotation.p4 != -2147483648) return "fail 4, expected = ${-2147483648}, actual = ${annotation.p4}"
    if (annotation.p5 != 2147483648.toLong()) return "fail 5, expected = ${2147483648}, actual = ${annotation.p5}"
    if (annotation.p6 != java.lang.Long.MAX_VALUE + 1) return "fail 5, expected = ${java.lang.Long.MAX_VALUE + 1}, actual = ${annotation.p6}"
    return "OK"
}



//File Ann.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface Ann {
   int p1();

   int p2();

   int p3();

   int p4();

   long p5();

   long p6();
}
