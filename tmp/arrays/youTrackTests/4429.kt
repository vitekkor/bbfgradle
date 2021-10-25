// Original bug: KT-34905

fun main(args: Array<String>) {
    lookAtMe { val c = "c" }
}


inline fun lookAtMe(f: String.() -> Unit) {
    "123".f()     // Put breakpoint here
    val b = "b"
}
