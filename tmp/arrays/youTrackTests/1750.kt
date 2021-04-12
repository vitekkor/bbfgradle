// Original bug: KT-30752

class Foo {
  private val runnable1 = object : Runnable {
    override fun run() {
    }
  }

  fun foo() {
    val runnable2 = object : Runnable {
      override fun run() {
      }
    }
  }

  val runnable3 = object : Runnable {
    override fun run() {
    }
  }
}
