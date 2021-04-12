// Original bug: KT-23535

class Foo {
  private val list = createList()

  private fun createList(): List<String> {
    return listOf("foo", list[0])
  }
}

fun main(args: Array<String>) {
  Foo()  
}
