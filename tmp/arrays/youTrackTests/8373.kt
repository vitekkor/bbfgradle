// Original bug: KT-17370

abstract class Parent(private val name: String) {
  fun doAll() {
    println("Sending ${getMessage()} to $name")
  }

  abstract fun getMessage(): String
}

class ChildFoo(name: String) : Parent(name) {
  override fun getMessage() = "foo"
}

class ChildBar(name: String) : Parent(name) {
  override fun getMessage() = "bar"
}
