// Original bug: KT-10627

fun <T> Array<T>.operationOnInvariant(f: (T) -> Int) {}
fun useOperation(arr: Array<out String>) {
    val f: (String) -> Int = { s: String -> s.length }
    arr.operationOnInvariant(f)
    arr.operationOnInvariant { s: String -> s.length }
                            // ^^ Expected parameter of type kotlin.Any?
}
