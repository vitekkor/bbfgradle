// Original bug: KT-23245

fun main(args: Array<String>) {
    if (args.size == 0) Seal.HarborSeal
    Seal.npe()
}

sealed class Seal {
    object HarborSeal : Seal()

    companion object {
        private val cute = arrayOf(HarborSeal)
        fun npe() = cute.firstOrNull { it.toString() == "" }
    }
}
