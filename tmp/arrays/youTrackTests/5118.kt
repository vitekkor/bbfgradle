// Original bug: KT-20478

import Owner.E.*

class Owner {
    private enum class E /* not used */ {
        FIRST
    }

    fun foo(e: Any) {
        when (e) {
            FIRST -> println()
        }
    }
}
