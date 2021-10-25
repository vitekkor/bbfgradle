// Original bug: KT-27032

fun case_1(y: Int?) {
    val x1 = null
    val x2 = null

    if (y != x1 ?: x2) { // ` x1 ?: x2 ` is inferred to Any?, not Nothing?
        println(y.inv()) // unsafe call
    }

    val x3 = x1 ?: x2 // ` x1 ?: x2 ` is inferred to Nothing?

    if (y != x3) {
        println(y.inv()) // OK
    }
}
