// Original bug: KT-33155

fun main() {
    object {
        fun one() =
            let {
                object {
                    fun oneTwo() {
                        two()
                    }
                }
            }
        fun two() {}
    }.one()
}
