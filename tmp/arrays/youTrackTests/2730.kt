// Original bug: KT-15020

interface A {
  fun f(): Int
}

class B : A {
  override fun f() = listOf(1, 2, 3).find { it % 2 == 0 }.let {
    (it as Int).toInt()
    it as Int    // smart cast fails if "as Int" part is removed
  }
}
