// Original bug: KT-26202

fun increment(
        value : () -> Int = { 0 },
        by : () -> Int = { 1 }
) = value() + by()

fun main(args: Array<String>) {

    // this will print 2
    println(
            increment({1})
    )

    // after applying IntelliJ's "move lambda out of parantheses":
    // no compile time error, but change in runtime behaviour. This prints "1" instead of "2"
    // which is actually more problematic for a programmer when he uses inspection hints
    println(
           increment {1}
    )

}
