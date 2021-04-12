// Original bug: KT-20872


inline fun (() -> Unit).testReceiver() = this()
inline fun testArg(it: (() -> Unit)) = it()

fun main(args: Array<String>) {
  { println("test") }.testReceiver() // not inlined
  testArg { println("test") } // inlined
}
