//File NoDocumented.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface NoDocumented {
}


//File ExplicitJavaDocumented.java
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ExplicitJavaDocumented {
}


//File ExplicitMustBeDocumented.java
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.MustBeDocumented;

@MustBeDocumented
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ExplicitMustBeDocumented {
}


//File ExplicitBoth.java
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.MustBeDocumented;

@MustBeDocumented
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ExplicitBoth {
}


//File Main.kt
// TARGET_BACKEND: JVM
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME
// FULL_JDK
// SKIP_JDK6

import java.lang.annotation.Documented

inline fun <reified A> isDocumented(): Boolean =
    A::class.java.getDeclaredAnnotation(Documented::class.java) != null

fun box(): String {
    if (isDocumented<NoDocumented>()) return "Fail NoDocumented"
    if (!isDocumented<ExplicitMustBeDocumented>()) return "Fail ExplicitMustBeDocumented"
    if (!isDocumented<ExplicitJavaDocumented>()) return "Fail ExplicitJavaDocumented"
    if (!isDocumented<ExplicitBoth>()) return "Fail ExplicitBoth"

    return "OK"
}

