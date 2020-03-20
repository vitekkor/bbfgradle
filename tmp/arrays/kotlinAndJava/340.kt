//File Bar.java
import kotlin.Metadata;

public final class Bar {
}


//File No.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface No {
   String value();
}


//File Foo.java
import kotlin.Metadata;

@Yes("OK")
@No("Fail")
public final class Foo {
}


//File Yes.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface Yes {
   String value();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JS, NATIVE
// WITH_REFLECT

import kotlin.reflect.full.findAnnotation
import kotlin.test.assertNull

fun box(): String {
    assertNull(Bar::class.findAnnotation<Yes>())
    assertNull(Bar::class.findAnnotation<No>())

    return Foo::class.findAnnotation<Yes>()?.value ?: "Fail: no annotation"
}

