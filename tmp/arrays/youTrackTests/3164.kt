// Original bug: KT-28252

fun test(a: Int, b: Int?, c: Any) {
    1 === 2 // Error
    1.0 === 1.0 // Error
    a === a // Error
    a !== a // Error
    a === b // Error
    a === c // Error
    b === c // Error
}
