// Original bug: KT-20529

class EqWhen(val a: String, val b: String) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            other === this -> true
            else -> false
        }
    }
}
