// Original bug: KT-33234

fun kek() {
  listOf(1, 2, 3).map {
    if (it == 1)
      return

    if (it == 2)
      return@map 2

    if (it == 3)
      listOf(1, 2).filter {
        listOf(1, 2, 3).filter {
          return@filter false
        }
        return@filter false
      }

    1
  }

  a@ for (i in 1..2) {
    break@a
    continue@a
  }
}
