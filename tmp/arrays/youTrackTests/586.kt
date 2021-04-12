// Original bug: KT-42963

fun main() {
    var a = true
    if (true) { // arbitrary value, not explicitly 'always true'
        a = true // false negative: Should be marked as redundant
    }
    print(a)
}
