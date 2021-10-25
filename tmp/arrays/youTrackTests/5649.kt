// Original bug: KT-31177

internal interface InterfaceWDefaults {
    // abstract method
    fun square(a: Int)

    // default method
    fun show() {
        println("Default Method Executed")
    }
}
