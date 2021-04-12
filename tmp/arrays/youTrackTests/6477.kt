// Original bug: KT-28598

fun case_1(a: MutableList<out MutableList<MutableList<MutableList<MutableList<Int?>?>?>?>?>?) {
    if (a != null) {
        val b = a[0]
        if (b != null) {
            val c = b[0]
            if (c != null) {
                val d = c[0]
                if (d != null) {
                    val e = d[0]
                    if (e != null) {
                        val f = e[0]
                        if (f != null) {
                            f // inferred to Int
                        }
                    }
                }
            }
        }
    }
}
