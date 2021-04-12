// Original bug: KT-5268

package digitalfusion.app

open class Parent(val name: String) {
  val fullname = buildFullName();
  open fun buildFullName(): String = "";
}
class Child(val input: String): Parent(input) {
  override fun buildFullName(): String {
    return input.toUpperCase();
  }
}

fun main(vararg args: String) {
  val child = Child("matt gile");
  println("name=${child.input}")
  println("name=${child.name}")
  println("name=${child.fullname}")
}
