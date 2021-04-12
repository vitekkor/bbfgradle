// Original bug: KT-20245

fun crash() = f {}

inline fun f(crossinline b: () -> Unit) {
    object : Object() {
        val o = object : Object() {
            override fun hashCode(): Int {
                b()
                return super.hashCode()
            }
        }
    }
}
