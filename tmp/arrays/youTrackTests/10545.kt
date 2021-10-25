// Original bug: KT-3706

package t

class C {
    fun f(): Int {
        try {
            return 0
        }
        finally {
            try { // culprit ?? remove this try-catch and it works.
            } catch (ignore: Exception) {
            }
        }
    }
}

fun main(args: Array<String>) { C() }
