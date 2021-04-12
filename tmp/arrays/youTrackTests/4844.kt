// Original bug: KT-35440

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
