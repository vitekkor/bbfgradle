// Original bug: KT-34905

package noParameterLambdaArgumentCallInInline

fun main(args: Array<String>) {
    lookAtMe {
        val c = "c"
    }
}

inline fun lookAtMe(f: String.() -> Unit) {
    val a = "a"

    "123".      //Breakpoint!  Step over jumps inside lambda but should to `val b = "b"` as would be in case of one line   `"123".f()`
        f()
    val b = "b"
}
