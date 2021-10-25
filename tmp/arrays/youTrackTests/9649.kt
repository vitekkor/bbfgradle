// Original bug: KT-7265

fun test(b: Boolean): (Int) -> Int {
    return if (b) {
        fun(n: Int) = n + 1 // Function declaration must have name here
    }
    else {
        fun(n: Int) = n - 1 // Function declaration must have name here
    }
}
