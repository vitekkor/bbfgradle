// Original bug: KT-43776

    val timestamps = listOf<Double>().toMutableList()

    fun get(time: Double): Double? {
        timestamps.withIndex().firstOrNull{ it.value >= time}

         return 1.0;
    }
