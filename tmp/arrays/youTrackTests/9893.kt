// Original bug: KT-9574

public inline fun <A, T> ((A) -> T).toProvider(arg: A): () -> T = { invoke(arg) }

public fun main(args: Array<String>) {
    val f = { i: Int -> "[$i]" }
    val p = f.toProvider(42)
    println(p())
}
