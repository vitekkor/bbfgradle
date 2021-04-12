// Original bug: KT-998

fun findPairless(a : IntArray) : Int {
  for (i in a.indices) {
    var pair = false
    for (j in a.indices) {
      if (i != j && a[i] == a[j]) {
        pair = true
        break
      }
    }
    
    if (!pair)
      return a[i]
  }
  return -1
}
