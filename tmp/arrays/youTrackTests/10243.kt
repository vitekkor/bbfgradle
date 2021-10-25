// Original bug: KT-1030

public fun<TItem, TResult> (()->Iterable<TItem>).select(selector : (TItem)->TResult) : ()->Iterable<TResult>
{
    return { this().map { selector(it) } }
}

fun main(args: Array<String>) {
    println({ listOf(1) }.select { it*2 }())
}
