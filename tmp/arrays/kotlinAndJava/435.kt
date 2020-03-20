//File MyClass.java
import kotlin.Metadata;

@Ann(
   b = 1,
   s = 1,
   i = 1,
   f = 1.0F,
   d = 1.0D,
   l = 1L,
   c = 'c',
   bool = true
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
    if (ann.b != 1.toByte()) return "fail: annotation parameter b should be 1, but was ${ann.b}"
    if (ann.s != 1.toShort()) return "fail: annotation parameter s should be 1, but was ${ann.s}"
    if (ann.i != 1) return "fail: annotation parameter i should be 1, but was ${ann.i}"
    if (ann.f != 1.toFloat()) return "fail: annotation parameter f should be 1, but was ${ann.f}"
    if (ann.d != 1.0) return "fail: annotation parameter d should be 1, but was ${ann.d}"
    if (ann.l != 1.toLong()) return "fail: annotation parameter l should be 1, but was ${ann.l}"
    if (ann.c != 'c') return "fail: annotation parameter c should be 1, but was ${ann.c}"
    if (!ann.bool) return "fail: annotation parameter bool should be 1, but was ${ann.bool}"
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
   byte b();

   short s();

   int i();

   float f();

   double d();

   long l();

   char c();

   boolean bool();
}
