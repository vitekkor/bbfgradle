// Original bug: KT-14201

interface Intf {
  val aValue: List<Int>
}

abstract class ClassA {
  abstract val aValue: Intf
}

abstract class Invocable {
  abstract operator fun invoke(): Int
}

class ClassB : ClassA() {
  val x = object : Invocable() {
    override operator fun invoke() = 3
  }
  override val aValue: Intf = object : Intf {
    override val aValue = listOf(x()).map { it + x() }
  }
}
