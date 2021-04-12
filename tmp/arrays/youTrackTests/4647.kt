// Original bug: KT-35766

fun main() {
    val u = "abc" // for some reason, this line is necessary for reproduction
    var t = 22 // breakpoint, not on the next line because the debugger wouldn't stop there: KT-33055
    with(R()) { }
}

data class R(
    var c: Int = 0
)
