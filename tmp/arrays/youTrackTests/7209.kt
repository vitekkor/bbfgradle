// Original bug: KT-24343

package debug

inline fun simplest() {} // inline is essential to reproduce

fun main(args: Array<String>) {
    simplest() // Breakpoint A.
    simplest() // Breakpoint B: essential to reproduce.
}
