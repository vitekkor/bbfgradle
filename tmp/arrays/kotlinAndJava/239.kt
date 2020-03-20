//File A.java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Target;

@Target(
   allowedTargets = {AnnotationTarget.TYPE}
)
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.TYPE_USE})
public @interface A {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// WITH_RUNTIME

fun box(): String {
    A::class.java.declaredAnnotations.joinToString()
    ExtensionFunctionType::class.java.declaredAnnotations.joinToString()
    return "OK"
}

