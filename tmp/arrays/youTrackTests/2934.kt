// Original bug: KT-37018

inline class Z(val x: Int) {
    private fun String.privateExtensionFun() {}
    private val String.privateExtensionVal: Int get() = x
    private var String.privateExtensionVar: Int
        get() = x
        set(v) {}
}
