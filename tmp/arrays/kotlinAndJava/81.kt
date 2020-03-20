//File Main.kt
import kotlin.test.assertEquals

fun foo(x : Season, block : (Season) -> String) = block(x)

fun box() : String {
    return foo(Season.SPRING) {
        x -> when (x) {
            Season.SPRING -> "OK"
            Season.SUMMER -> "fail" // redundant branch to force use of TABLESWITCH instead of IF_ICMPNE
            else -> "fail"
        }
    }
}

// 1 TABLESWITCH



//File Season.java
import kotlin.Metadata;

public enum Season {
   WINTER,
   SPRING,
   SUMMER,
   AUTUMN;
}
