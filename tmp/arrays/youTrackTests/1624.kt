// Original bug: KT-25738

sealed class BinaryState : Comparable<BinaryState> {
    object On : BinaryState() {
        override fun compareTo(other: BinaryState) =
            when (other) {
                is On -> 0
                is Off -> 1
            }
    }

    object Off : BinaryState() {
        override fun compareTo(other: BinaryState) =
            when (other) {
                is On -> -1
                is Off -> 0
            }
    }

    companion object {
        val range = Off..On
    }
}

fun main(args: Array<String>) {
    BinaryState.On
}
