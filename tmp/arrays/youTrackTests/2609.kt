// Original bug: KT-36430

fun main(args: Array<String>) {
  val s = args.size
  when {
    s in 1 .. 2 -> println("few")
    s in 5 .. 6 -> println("some")
    else -> println("other")
  }
}
