//File MyClass.java
import kotlin.Metadata;

@Ann(
   args = {O.class, K.class}
)
public final class MyClass {
}


//File O.java
import kotlin.Metadata;

public final class O {
}


//File K.java
import kotlin.Metadata;

public final class K {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_REFLECT

import kotlin.reflect.KClass

fun box(): String {
    val args = MyClass::class.java.getAnnotation(Ann::class.java).args
    val argName1 = args[0].simpleName ?: "fail 1"
    val argName2 = args[1].simpleName ?: "fail 2"
    return argName1 + argName2
}



//File Ann.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface Ann {
   Class[] args();
}
