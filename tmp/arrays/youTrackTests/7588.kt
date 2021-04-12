// Original bug: KT-22361

class C(val a: Array<Any>) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is C) return false

    if (!java.util.Arrays.equals(a, other.a)) return false
//       ^^^^^^^^^^^^^^^^

    return true
  }

  override fun hashCode(): Int {
    return java.util.Arrays.hashCode(a)
//         ^^^^^^^^^^^^^^^^
  }
}
