// Original bug: KT-3036

package testDebug

fun test(param: Int): String {
    if (param < 0) {
        return "Negative"
    }

    if (param == 0) {
        return "Zero"
    }

    return "Positive"
}

fun main(args: Array<String>) {
    test(5)
}
