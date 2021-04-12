// Original bug: KT-38869

open class BaseList : AbstractList<String>() {
  override val size: Int
    get() = 1

  override fun get(index: Int): String {
    return ""
  }

  open fun remove(index: Int): String {
    println("Removing at $index")
    return ""
  }

  open fun remove(e: String): Boolean {
    println("Removing $e")
    return true
  }
}

open class DerivedList : BaseList()

fun main() {
  val list = DerivedList()
  list.remove("1")
}
