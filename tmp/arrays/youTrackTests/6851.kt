// Original bug: KT-15188

abstract class Base(number: Int)

class One : Base(ONE) {
  companion object {
    const val ONE = 1
  }
}

class Two : Base(TWO) {
  companion object {
    private const val TWO = 2
  }
}
