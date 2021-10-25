// Original bug: KT-34392

var a = listOf(1)
fun test() {
    a += 2 // Suggested: "Replace with ordinary assignment"

    var b = listOf(1)
    b += 2 // Suggested: "Change type to multiple" and "Join with initializer"
}
