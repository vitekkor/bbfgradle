//File A.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface A {
   String value() default "OK";
}


//File Test.java
import kotlin.Metadata;

@A
public final class Test {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

const val z = "OK"

fun box(): String {
    return Test::class.java.getAnnotation(A::class.java).value
}

