// Original bug: KT-17730

  fun foo(): Boolean {
    val list = listOf(1, 2, 3, 4, 5)
    for (e in list) {
      if (!(e <= 3)) {
        return false
      }
    }
    return true
  }
