// Original bug: KT-35043

interface INext<T> {

  fun next(n: Int): T

  val T.next
    get() = next(1)

}


object IntNext : INext<Int> {

  override fun next(n: Int): Int {
    return n + 1
  }

}

