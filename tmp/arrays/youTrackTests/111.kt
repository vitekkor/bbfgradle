// Original bug: KT-21836

package interfaces

interface I {
    fun iFun(): String = "I::iFun"
}

/* private */interface PI {
    fun piFun(): Any
    fun iFun(): String = "PI::iFun"
}
open class OpenClassI : I {
    override fun iFun(): String = "OpenClassI::iFun"
}

open class MultiExtClass : OpenClassI(), PI {
    override fun piFun(): Any {
        return 42
    }

    override fun iFun(): String = super<PI>.iFun()
}

