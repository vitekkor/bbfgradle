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

fun f() = A.OK

fun box(): String {
    return when (f()) {
        B.FAIL -> "fail"
        A.OK -> "OK"
    }
}

// 0 TABLESWITCH
// 0 LOOKUPSWITCH

