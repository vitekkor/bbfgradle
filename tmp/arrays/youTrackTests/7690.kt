// Original bug: KT-26202

fun twoLambdas(
        first : (Int) -> Boolean = {false},
        second : () -> Int = {5}
) = first(second())

fun main(args: Array<String>) {
    twoLambdas({x->true})
}
