// Original bug: KT-30839

@file:Suppress("PublicApiImplicitType", "MemberVisibilityCanBePrivate")

class Error(val code: Int) {
    override fun toString() = "Error code is $code"
}

class Errors(code: Int, val error: Error = error(code)) {
    fun error(code: Int) = Error(code)
}

fun main() {
    Errors(1)
}
