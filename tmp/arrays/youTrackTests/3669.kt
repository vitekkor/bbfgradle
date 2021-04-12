// Original bug: KT-37854

fun main() {
    Fooo().function(42)
}

class Fooo {
    fun function(param: Int) {
        val local = "hi"
        val a = "$local test"
        println("$local test") // breakpoint here
    }
}
