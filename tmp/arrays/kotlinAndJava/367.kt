//File Anno.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface Anno {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS, NATIVE

// WITH_REFLECT

import kotlin.test.assertEquals

fun box(): String {
    val a = Anno::class.annotations

    if (a.size != 1) return "Fail 1: $a"
    val ann = a.single() as? Retention ?: return "Fail 2: ${a.single()}"
    assertEquals(AnnotationRetention.RUNTIME, ann.value)

    return "OK"
}

