//File MyClass.java
import kotlin.Metadata;

@Ann(
   i = 1
)
public final class MyClass {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val ann = MyClass::class.java.getAnnotation(Ann::class.java)
    if (ann == null) return "fail: cannot find Ann on MyClass}"
    if (ann.i != 1) return "fail: annotation parameter i should be 1, but was ${ann.i}"
    return "OK"
}

const val i2: Int = 1
const val i: Int = i2



//File Ann.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface Ann {
   int i();
}
