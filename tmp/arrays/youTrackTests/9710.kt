// Original bug: KT-3003

fun test(i: Int, flag: Boolean) : String {
    return when (i) {
        else -> {
            if (flag) {
                "flag True!".toString() // Without toString() there's "UNUSED_EXPRESSION" warning
            }

            "flag False!"
        }
    }
}

fun testOther(i: Int, flag: Boolean) =  when (i) {
    else -> {
        if (flag) {
            "flag True!".toString()
        }

        "flag False!"
    }
}

fun main(args: Array<String>) {
    println(test(10, true))
    println(test(10, false))
}
