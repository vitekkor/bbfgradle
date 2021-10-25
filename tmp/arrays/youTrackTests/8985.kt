// Original bug: KT-15085

    fun count() {
        var cnt = 0
        count@ while (true) {
            cnt++
            if (cnt > 10) break@count  // ERROR: The label '@count' does not denote a loop
        }
    }
