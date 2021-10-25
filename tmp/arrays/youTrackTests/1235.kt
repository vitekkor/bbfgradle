// Original bug: KT-32749

package a.b.c

fun main() {
  Class.forName("a.b.c.X")
}

class X {
  val num = 42
  val map: Int = 1.apply {
    object : Y({ true }) {
      override fun fun1() {
        println(num)
      }
    }
  }
}

abstract class Y(val lambda: () -> Boolean) {
  abstract fun fun1()
}
