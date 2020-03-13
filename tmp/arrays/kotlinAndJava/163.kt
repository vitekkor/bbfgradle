//File A.java
import kotlin.Metadata;

public final class A {
}


//File MyEnum.java
import kotlin.Metadata;

public enum MyEnum {
   A;
}


//File MyClass.java
import kotlin.Metadata;

@Ann
public final class MyClass {
}


//File Ann2.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface Ann2 {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

import kotlin.reflect.KClass

fun box(): String {
    val ann = MyClass::class.java.getAnnotation(Ann::class.java)
    if (ann == null) return "fail: cannot find Ann on MyClass}"
    if (ann.i != 1) return "fail: annotation parameter i should be 1, but was ${ann.i}"
    if (ann.s != "a") return "fail: annotation parameter s should be \"a\", but was ${ann.s}"
    val annSimpleName = ann.a.annotationClass.java.getSimpleName()
    if (annSimpleName != "Ann2") return "fail: annotation parameter a should be of class Ann2, but was $annSimpleName"
    if (ann.e != MyEnum.A) return "fail: annotation parameter e should be MyEnum.A, but was ${ann.e}"
    if (ann.c.java != A::class.java) return "fail: annotation parameter c should be of class A, but was ${ann.c}"
    if (ann.ia[0] != 1 || ann.ia[1] != 2) return "fail: annotation parameter ia should be [1, 2], but was ${ann.ia}"
    if (ann.sa[0] != "a" || ann.sa[1] != "b") return "fail: annotation parameter ia should be [\"a\", \"b\"], but was ${ann.sa}"
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
   int i() default 1;

   String s() default "a";

   Ann2 a() default @Ann2;

   MyEnum e() default MyEnum.A;

   Class c() default A.class;

   int[] ia() default {1, 2};

   String[] sa() default {"a", "b"};
}
