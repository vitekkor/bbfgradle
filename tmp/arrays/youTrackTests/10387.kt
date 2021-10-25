// Original bug: KT-6154

fun main(args : Array<String>) {
  P().main()
}

class P {
  private val FOO = ""
  
  private inline fun foo() {
    println(FOO)
  }
  
  fun main() {
      foo()
  }
}
