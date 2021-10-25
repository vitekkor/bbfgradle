// Original bug: KT-35321

fun getSomeCoolTask(v: Boolean): (String) -> Int {
    return when (v) {
        true -> {{ taskOne(it) }}  // "Remove braces from 'when' entry
        false -> { x -> taskTwo(x)}  // "Replace explicit parameter 'x' with 'it'"
    }
}
fun taskOne(s: String) = s.length
fun taskTwo(s: String) = 42
fun main() {
    getSomeCoolTask(true)("Hello")
}

