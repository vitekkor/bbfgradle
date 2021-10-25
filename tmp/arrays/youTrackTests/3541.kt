// Original bug: KT-38563

class Outer {
    inner class Inner {
        fun exec() {
            println("Outer class = $this")
        }
    }
}
// prints: Outer class = Outer$Inner@63947c6b
