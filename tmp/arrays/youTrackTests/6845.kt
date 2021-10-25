// Original bug: KT-13973

fun main(args: Array<String>) {
    var n = 1 // capture _variable_
    class Rec() { // in a nested class
        fun compute() {
            if (n < 1) // must combine operation on captured var
                Rec() // with creation of the class
        }
    }
    Rec()
}
