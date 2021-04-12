// Original bug: KT-42175

operator fun <T> SequenceScope<String>.plusAssign(x: SequenceScope<T>) {}
fun main() {
     val x = sequence {
         yield("result")
         this += this // AssertionError
     }
}
