// Original bug: KT-44974

// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// SAM_CONVERSIONS: INDY

fun interface IExtFun {
    fun String.foo(s: String) : String
}

fun box() {
    val oChar = 'O'
    val iExtFun = IExtFun { this + oChar.toString() + it }
    return with(iExtFun) {
        "".foo("K")
    }
}
