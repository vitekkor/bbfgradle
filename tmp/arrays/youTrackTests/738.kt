// Original bug: KT-43868

package localFunction

fun main() {
    val x = 1
    fun bar(): Int {
        val y = x
        return y + 1
    }

    //Breakpoint!
    val a = x + bar()
}

// EXPRESSION: bar()
// RESULT: 2: I
