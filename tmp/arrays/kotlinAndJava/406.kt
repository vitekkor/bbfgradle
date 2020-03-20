//File MyClass.java
import kotlin.Metadata;

@Ann(
   p1 = Integer.MIN_VALUE,
   p2 = 2,
   p4 = 2L,
   p5 = 2
)
public final class MyClass {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val annotation = MyClass::class.java.getAnnotation(Ann::class.java)!!
    if (annotation.p1 != -2147483648) return "fail 1, expected = ${-2147483648}, actual = ${annotation.p1}"
    if (annotation.p2 != 2) return "fail 2, expected = ${2}, actual = ${annotation.p2}"
    if (annotation.p4 != 2.toLong()) return "fail 4, expected = ${2}, actual = ${annotation.p4}"
    if (annotation.p5 != 2) return "fail 5, expected = ${2}, actual = ${annotation.p5}"
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

   long p4();

   int p5();
}
