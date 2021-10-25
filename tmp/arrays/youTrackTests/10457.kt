// Original bug: KT-5576

fun test2(a: Boolean, b: Boolean, c: Boolean) {
    val a =
            if (a) {
                if (b) {
                    "1"
                } else if (c) {
                    "2"
                } else {
                    throw Exception("Rest parameter must be array types")
                }
            }
            else {
                "3"
            }
}
