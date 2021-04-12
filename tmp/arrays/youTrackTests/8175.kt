// Original bug: KT-17864

fun printStringOrException(f: () -> Any) {
    try {
        println(f())
    } catch (e : Throwable) {
        println(e)
    }
}

fun main(args: Array<String>) {

    val s = "test"

    val emptyRanges = arrayOf(
            IntRange.EMPTY, 2..1, 20..10, 0..-1, -1..-10
    )

    //All empty ranges are equal and sets/maps store them once!
    for (r in emptyRanges)
        printStringOrException {"$r == IntRange.EMPTY -> ${r == IntRange.EMPTY}"}
    println()

    for (r in emptyRanges){
        print("s = \"$s\", r = $r, substring : ")
        printStringOrException {s.substring(r)}
    }
    println()

    // subsequence exactly the same as substring
    for (r in emptyRanges){
        print("s = \"$s\", r = $r, subSequence : ")
        printStringOrException {s.subSequence(r)}
    }
    println()

    // for empty string
    for (r in emptyRanges){
        print("s = \"\", r = $r, substring : ")
        printStringOrException {"".substring(r)}
    }
    println()

    // but slice works
    for (r in emptyRanges){
        print("s = \"$s\", r = $r, slice : ")
        printStringOrException {s.slice(r)}
    }
    println()

    // list slicing works as expected
    val list = listOf(1,2,3,4)
    for (r in emptyRanges){
        print("list = \"$list\", r = $r, slice : ")
        printStringOrException {list.slice(r)}
    }
    println()

}

