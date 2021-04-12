// Original bug: KT-20573

object LambdaTest {
  fun test(f: () -> Unit): LambdaTest = this
}

val test = LambdaTest.test {
  println("1")
}.test { println("2") }
    .test {
      println("3")
    }.test {
      println("4")
    }
