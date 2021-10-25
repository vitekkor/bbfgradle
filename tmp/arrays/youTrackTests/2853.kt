// Original bug: KT-40167

fun main() {
  val test: Map<String, H> = emptyMap<String, H>().withDefault{ H() }
  println(test.getValue("foo").freq.getValue("bar") == 0)
  println(test.getValue("foo").getValue("bar") == 0) //NoSuchElement Exception
}

data class H(val op: String = "empty", val freq: Map<String, Int> = emptyMap<String, Int>().withDefault{ 0 } ): Map<String, Int> by freq
