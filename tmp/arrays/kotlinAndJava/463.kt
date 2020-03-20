//File MyClass.java
import kotlin.Metadata;

@Ann(
   b = 1,
   s = 1,
   i = 1,
   l = 1L
)
public final class MyClass {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val annotation = MyClass::class.java.getAnnotation(Ann::class.java)!!
    if (annotation.b != 1.toByte()) return "fail 1"
    if (annotation.s != 1.toShort()) return "fail 2"
    if (annotation.i != 1) return "fail 2"
    if (annotation.l != 1.toLong()) return "fail 2"
    return "OK"
}

// EXPECTED: Ann[b = IntegerValueType(1): IntegerValueType(1), i = IntegerValueType(1): IntegerValueType(1), l = IntegerValueType(1): IntegerValueType(1), s = IntegerValueType(1): IntegerValueType(1)]



//File Ann.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface Ann {
   byte b();

   short s();

   int i();

   long l();
}
