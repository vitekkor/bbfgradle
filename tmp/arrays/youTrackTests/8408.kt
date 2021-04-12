// Original bug: KT-21716

package alraune

object BloodyBug {
    // If `doStuff` function is commented out, stuff works
    fun doStuff(f: () -> Unit = {}) = DoStuff()

    class DoStuff(f: () -> Unit = {})

    @JvmStatic fun main(args: Array<String>) {
        DoStuff {}    ; println("First works")
        DoStuff()     ; println("Second works")
    }
}
