//File Name.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
   String value();
}


//File Anno.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {
   @Name("O")
   String o();

   @Name("K")
   String k();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// WITH_RUNTIME

import kotlin.test.assertEquals

fun box(): String {
    val ms = Anno::class.java.declaredMethods

    return (ms.single { it.name == "o" }.annotations.single() as Name).value +
           (ms.single { it.name == "k" }.annotations.single() as Name).value
}

