// Original bug: KT-19473

class Test() {
    var value: Int = 0
    
    fun myfun1() : Int {
        value += 1
        return value
    }
    fun myfun2() : Int {
        value += 2
        return value
    }
}

var instance = Test()

fun tryWithThis(s: String) {
    var NOMATCH: () -> Int = { 0 }
    var pf: () -> Int = when(s) {    
    	"1" -> instance::myfun1
        "2" -> instance::myfun2
        else -> NOMATCH
    }
    print(s + " ")
    println(pf())
}
fun main(args: Array<String>) {
    tryWithThis("0") // Outputs 0 0
    tryWithThis("1") // Outputs 1 1
    tryWithThis("2") // Outputs 2 3
}
