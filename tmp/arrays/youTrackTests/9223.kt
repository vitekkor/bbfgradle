// Original bug: KT-15513

fun box(): String {
    OUTER@while (true) {
        var x = ""
        try {
            do {
                x = x + break@OUTER
            } while (true)
        } finally {
            return "OK"
        }
    }
}
