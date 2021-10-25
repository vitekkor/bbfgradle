// Original bug: KT-6947

fun main(args : Array<String>) {
  val foo: Foo = Foo()
  
  val bar: Bar = Bar("Hello Kotlin!")
  
  foo.doWork(bar::printText)
}

class Foo {
  fun doWork(work: () -> Unit) {
    work()
  }
}
          
class Bar (val text: String) {
  fun printText() {
    println("${text}")
  }
}
