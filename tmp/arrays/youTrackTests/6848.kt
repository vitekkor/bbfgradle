// Original bug: KT-14150

class CompilerBug {
    internal var `var` = 0

    inner class Inner @JvmOverloads constructor(r: Runnable = Runnable { `var` = 1 })

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            CompilerBug().Inner()
        }
    }
}
