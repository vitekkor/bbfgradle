//File Main.kt


fun foo(): Season = Season.SPRING

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
