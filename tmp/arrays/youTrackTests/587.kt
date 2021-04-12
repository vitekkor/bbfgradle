// Original bug: KT-42963

fun main() {
    var a: Boolean
    a = true // true positive: The value 'true' assigned to 'var a: Boolean defined in main' is never used
    a = true 
    print(a)
}
