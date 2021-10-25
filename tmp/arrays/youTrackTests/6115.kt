// Original bug: KT-7857

enum class X { A, B }
fun foo(arg: X?): Int {
    if (arg != null) {
        return when (arg) {
            X.A -> 1
            X.B -> 2
            // else or null branch is required here!
        }
    } 
    else {
        return 0
    }
}
