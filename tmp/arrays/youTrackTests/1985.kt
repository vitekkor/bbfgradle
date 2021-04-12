// Original bug: KT-21029

object Namespace {
  
  fun enter(block: Namespace.() -> Unit) {
    block()
  }
  
  fun <T> enter(block: Namespace.() -> T): T = block()

  fun <P, T> withParam(param: P, block: Namespace.(P) -> T): T {
    return block(param)
  }
}
