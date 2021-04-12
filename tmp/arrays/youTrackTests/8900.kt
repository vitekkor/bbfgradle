// Original bug: KT-17983

fun createBananasFromEmptyList(): List<Int> {
  return emptyList<Int>()
    .flatMap<Int, Int> {
      return emptyList()
    }
}

fun createOrangesFromEmptyList(): List<Int> {
  return emptyList<Int>()
    .flatMap<Int, Int> {
      emptyList()
    }
}
