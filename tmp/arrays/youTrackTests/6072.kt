// Original bug: KT-24964

// module1
typealias Dispatch<Msg> = (Msg) -> Unit
typealias Effect<Msg> = (Dispatch<Msg>) -> Unit

fun <Msg> noEffect(): Effect<Msg> = TODO()

// module2
fun test() {
    val s = { noEffect<Unit>() } // Exception from the compiler
}
