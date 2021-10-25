// Original bug: KT-18665

data class Example(private val str: String) {
  fun doWithExample(block : (Example) -> Unit) = block(Example("hello"))

  fun runExample() {
      doWithExample { example ->
          println(example.str)
      }
  }
}
