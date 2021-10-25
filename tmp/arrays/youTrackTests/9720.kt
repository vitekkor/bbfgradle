// Original bug: KT-11177

fun tailRecursive(n:Long) = 1

fun sequence(n:Int) = 2

fun test() {
  arrayOf(
          "tailRecursive" to { x: Long -> tailRecursive(x)},
          "sequence" to ::sequence
  )
}
