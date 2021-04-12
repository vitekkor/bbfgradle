// Original bug: KT-35467

fun main() {

     // extracts incorrectly - with no return statement
    val a = "".takeIf { true }
    val c = "".let { it }
    val d = "".run { this }
    val e: String = "".apply { this }

    // extracts correctly for all these copied extensions  
    val e2 = "".apply2 { this } 
}

inline fun <T> T.apply2(block: T.() -> Unit): T {
    block()
    return this
}
