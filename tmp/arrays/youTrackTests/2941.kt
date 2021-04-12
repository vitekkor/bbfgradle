// Original bug: KT-39913

package test

fun b(handler: I) {}

interface I { // Ctrl + Alt + Click shows "No implementations found"
    fun modeChanged() // Ctrl + Alt + Click leads to (1)
}
