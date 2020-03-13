//File M.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface M {
   int result();
}


//File Anno.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Target;

@Target(
   allowedTargets = {AnnotationTarget.PROPERTY}
)
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({})
public @interface Anno {
   String value();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// WITH_RUNTIME

fun box(): String =
    M::class.java.getAnnotation(Anno::class.java)?.value
        // TODO: fix KT-22463 and enable this test
        // ?: "Fail: no annotation"
        ?: "OK"

