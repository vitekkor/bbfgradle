// Original bug: KT-24833

interface A {
  val a: String
}

class C : A {
  @Suppress("RedundantModalityModifier")
  final override var a: String private set

  init {
    a = "something"
  }
}

