// Original bug: KT-40174

fun main() {
    fun printLine() { println("Top-level function") } // (1)
    class A {
        fun printLine() { println("Member function") } // (2)
        fun invokeFunction() = printLine()
    }
    
    A().invokeFunction() // invokes (1)
}
