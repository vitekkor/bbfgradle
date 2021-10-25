// Original bug: KT-12038

        val foos: List<String> = listOf()
        val map: Map<String, String> = mapOf()
        val bars: List<String> = foos.map { foo ->
            val b: String? = map[foo]
            if (b != null) {
                return@map b
            } else {
                return@map "abc"
            }
        } 
