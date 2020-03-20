//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// http://youtrack.jetbrains.com/issue/KT-2167

fun foo() = Season.SPRING

fun box() =
    if (foo() == Season.SPRING) "OK"
    else "fail"



//File Season.java
import kotlin.Metadata;

public enum Season {
   WINTER,
   SPRING,
   SUMMER,
   AUTUMN;
}
