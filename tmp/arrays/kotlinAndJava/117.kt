//File A.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface A {
}


//File B.java
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Target(
   allowedTargets = {AnnotationTarget.CLASS}
)
@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.TYPE})
public @interface B {
   A[] items() default {@A};
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val bClass = B::class.java
    val cClass = C::class.java

    val items = cClass.getAnnotation(bClass).items
    assert(items.size == 1) { "Expected: [A()], got ${items.asList()}" }
    assert(items[0] is A) { "Expected: [A()], got ${items.asList()}" }

    return "OK"
}



//File C.java
import kotlin.Metadata;

@B
public final class C {
}
