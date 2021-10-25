// Original bug: KT-19112

fun <caret>assignmentWithSum(n: Int): Int { // call `Data Flow to here` for a function `assignmentWithSum`
    var result = 0 // found - OK
    result += n    // NOT found, but expected
    return result
}
