// Original bug: KT-6942

fun main(args : Array<String>) {
  when (listOf(1)) {
    listOf(1) -> {
      println(true)
    }
    else ->
      println(false)
  }
    if (listOf(1) == listOf(1)) {
        println(true)
    }
    else {
    		println(false)
    }
}
