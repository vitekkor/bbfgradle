// Original bug: KT-40174

fun main() {
    class A {
        fun printLine() { println("Member function") } // (2)
        fun invokeFunction() = printLine()
    }
    fun printLine() { println("Top-level function") } // (1)

    A().invokeFunction() // invokes (2)

}
