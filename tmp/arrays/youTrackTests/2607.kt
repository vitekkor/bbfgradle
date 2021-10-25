// Original bug: KT-41126

var flag = false
enum class Case1(var x: Int) {
    VAL1(1) {
        init {
            flag = true
        }
    },
    VAL2(2);
    init {
        if (flag) {
            Case1.VAL1.x =5 //ok!
        }
    }
}

fun main() {
    println(Case1.VAL1.x) // printed 5
}

