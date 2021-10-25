// Original bug: KT-39677

abstract class SealedA
class SClassB: SealedA()
abstract class SClassC: SealedA()
open class SClassD: SealedA() {
  fun execute() {
    println("execute D")
  }
}

class SomeClass {
  lateinit var obj: SealedA

  fun doWorkAsClassD() {
    val objD = obj as SClassD
    objD.execute()
  }
}
