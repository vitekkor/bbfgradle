// Original bug: KT-32229

abstract class Base
class A : Base()
class B : Base()
class C : Base()

fun main() {
  val list = listOf(
    1 to { A() },
    2 to { B() },
    3 to { C() }
  )
}
