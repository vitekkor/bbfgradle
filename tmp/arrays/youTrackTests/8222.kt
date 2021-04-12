// Original bug: KT-22331

  fun getHighBound(): Int {
    return Int.MIN_VALUE
  }

  fun wrongRangeToInspection() {
    for (i in Int.MAX_VALUE - 1..getHighBound() - 1) {
      println(i)
    }
  }
