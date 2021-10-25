// Original bug: KT-10450

enum class Type { A, B, C }

fun Type.toInt(): Int {
  val result: Int
  when (this) {
    Type.A -> result = 1
    Type.B -> result = 2
    Type.C -> result = 3
  }
  return result
}
