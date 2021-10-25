// Original bug: KT-41450

class DexOk(){
  fun function(): () -> Unit {
    class Workaround : () -> Unit {
      override fun invoke() {
        println("Fail")
      }
    }

    return Workaround()
  }
}
