// Original bug: KT-38563

class Outer {
    inner class Inner {
        fun exec() {
            println("Outer class = ${this@Outer}")
        }
    }
}

fun main() {
    Outer().Inner().exec()
    // prints: Outer class = Outer@63947c6b
}
