//File foo.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface foo {
   String name();
}


//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Test {
   @foo(
      name = "OK"
   )
   public final void hello(@NotNull String input) {
      }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

import java.lang.annotation.Annotation

fun box(): String {
    val test = Test()
    for (method in Test::class.java.getMethods()!!) {
        val anns = method?.getAnnotations() as Array<Annotation>
        if (!anns.isEmpty()) {
            for (ann in anns) {
                val fooAnn = ann as foo
                return fooAnn.name
            }
        }
    }
    return "fail"
}

