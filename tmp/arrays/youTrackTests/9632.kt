// Original bug: KT-11748

class Test {
  fun bar1(numbers: List<Double>, name: String? = null) {
    System.out.println("Name was:${name?: "not given"}, numbers was ${numbers}")
  }
}

fun main(args: Array<String>) {
  val d:List<Double> = listOf(2.0)
  Test().bar1(listOf(2.0))
  Test().bar1(d)
}
