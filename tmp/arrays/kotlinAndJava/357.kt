//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// CHECK_CASES_COUNT: function=box count=0
// CHECK_IF_COUNT: function=box count=1

fun foo(): Season = Season.SPRING
fun bar(): Season = Season.SPRING

fun box() : String {
    when (foo()) {
        bar() -> return "OK"
        else -> return "fail"
    }
}



//File Season.java
import kotlin.Metadata;

public enum Season {
   WINTER,
   SPRING,
   SUMMER,
   AUTUMN;
}
