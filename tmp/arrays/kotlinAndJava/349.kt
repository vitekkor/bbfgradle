//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// CHECK_CASES_COUNT: function=box count=6
// CHECK_IF_COUNT: function=box count=1

fun box(): String {
    var res = ""
    // nullable variable
    val en2: Any? = En.A
    if (en2 is En) {
        when (en2) {
            En.A -> {res += "O"}
            En.B -> {}
            En.C -> {}
        }

        when (en2 as En) {
            En.A -> {res += "K"}
            En.B -> {}
            En.C -> {}
        }
    }

    return res
}



//File En.java
import kotlin.Metadata;

public enum En {
   A,
   B,
   C;
}
