//File MyClass10.java
import kotlin.Metadata;

@Ann(
   p = {1, 2}
)
public final class MyClass10 {
}


//File MyClass8.java
import kotlin.Metadata;

@Ann(
   p = {}
)
public final class MyClass8 {
}


//File MyClass9.java
import kotlin.Metadata;

@Ann(
   p = {1}
)
public final class MyClass9 {
}


//File MyClass2.java
import kotlin.Metadata;

@Ann(
   p = {1}
)
public final class MyClass2 {
}


//File MyClass6.java
import kotlin.Metadata;

@Ann(
   p = {1, 2}
)
public final class MyClass6 {
}


//File MyClass5.java
import kotlin.Metadata;

@Ann(
   p = {1}
)
public final class MyClass5 {
}


//File MyClass1.java
import kotlin.Metadata;

@Ann(
   p = {}
)
public final class MyClass1 {
}


//File MyClass4.java
import kotlin.Metadata;

@Ann(
   p = {}
)
public final class MyClass4 {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    test(MyClass1::class.java, "")
    test(MyClass2::class.java, "1")
    test(MyClass3::class.java, "12")

    test(MyClass4::class.java, "")
    test(MyClass5::class.java, "1")
    test(MyClass6::class.java, "12")

    test(MyClass8::class.java, "")
    test(MyClass9::class.java, "1")
    test(MyClass10::class.java, "12")

    return "OK"
}

fun test(klass: Class<*>, expected: String) {
    val ann = klass.getAnnotation(Ann::class.java)
    if (ann == null) throw AssertionError("fail: cannot find Ann on ${klass}")

    var result = ""
    for (i in ann.p) {
        result += i
    }

    if (result != expected) {
        throw AssertionError("fail: expected = ${expected}, actual = ${result}")
    }
}



//File MyClass3.java
import kotlin.Metadata;

@Ann(
   p = {1, 2}
)
public final class MyClass3 {
}


//File Ann.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface Ann {
   int[] p();
}
