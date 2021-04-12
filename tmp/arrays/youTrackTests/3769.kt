// Original bug: KT-27567

import Utils.append

fun main() {
    listOf<Int>().append(1) // Removing explicit type argument results in the same error
}

object Utils {

    inline fun <reified T> T.append(arg: Int): Int = appendUtil(arg)

    @JvmStatic
    @JvmOverloads
    fun <T> T.appendUtil(arg: Int = -1): Int = arg + 1
}
