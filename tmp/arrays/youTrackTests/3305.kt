// Original bug: KT-39297

    sealed class Sealed(val position: Int) {
        object Sealed1 : Sealed(1)
        object Sealed2 : Sealed(2)

        companion object {
            private val sealedByPosition = arrayOf(
                    Sealed1,
                    Sealed2
                ).associateBy { it.position }

            fun getByPosition(position: Int) = sealedByPosition.getValue(position)
        }
    }

// to reproduce just call:
val sealedByPosition = Sealed.Sealed1
