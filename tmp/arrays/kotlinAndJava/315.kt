//File A.java
import kotlin.Metadata;

public enum A {
   OK;
}


//File B.java
import kotlin.Metadata;

public enum B {
   FAIL;
}


//File Main.kt
// !LANGUAGE: -ProhibitComparisonOfIncompatibleEnums
// IGNORE_BACKEND_FIR: JVM_IR

fun f() = A.OK

fun box(): String {
    return when (f()) {
        B.FAIL -> "fail"
        A.OK -> "OK"
    }
}

