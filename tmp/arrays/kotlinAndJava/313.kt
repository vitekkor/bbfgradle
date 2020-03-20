//File Bar.java
import kotlin.Metadata;

public enum Bar {
   ONE,
   TWO;
}


//File Main.kt


fun isOne(i: Bar) = i == Bar.ONE

fun box(): String {
    return when (isOne(Bar.ONE) && !isOne(Bar.TWO)) {
        true -> "OK"
        else -> "Failure"
    }
}

