// Original bug: KT-36932

fun showTime(func: () -> Any) {
    val t = System.currentTimeMillis()
    val result = func()
    println("${System.currentTimeMillis() - t} ms: $result")
}

fun test(k: Int?, range: Iterable<Int>) = k in range

fun main(args: Array<String>) {
    val largeVal = 1000000000
    // This is fast
    showTime { largeVal in 0..largeVal }

    // This is slow (would be fixed by KT-34617)
    showTime { largeVal in 0..largeVal step 2 }
    // This is slow too (calls contains extension which iterates)
    showTime { test(largeVal, 0..largeVal) }
}
