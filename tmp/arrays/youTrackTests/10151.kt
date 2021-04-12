// Original bug: KT-9074

open class ErrorAdder {
  open fun addError(error: Throwable?): Unit = println("Base ${error}")
}
class MyErrorAdder : ErrorAdder() {
  override fun addError(error: Throwable?): Unit =
      when (error) {
        null -> throw IllegalArgumentException("Error can not be null")
        is NullPointerException -> super.addError(RuntimeException(error))
        is IllegalAccessError -> super.addError(RuntimeException(error))
        else -> super.addError(RuntimeException(error))
      }
}

fun main(args: Array<String>) {
  val adder = MyErrorAdder()
  adder.addError(IllegalArgumentException())
  adder.addError(null)
}

// Output:
// Base java.lang.RuntimeException: java.lang.IllegalArgumentException
// Exception in thread "main" java.lang.IllegalArgumentException: Error can not be null
