// Original bug: KT-21819

package interfaces

private interface PI {
    fun piFun(): Any
    fun iFun(): String = "PI::iFun"
}

open class MultiExtClass : PI {
    override fun piFun(): Any {
        return 42
    }

    override fun iFun(): String = super<PI>.iFun()
}
