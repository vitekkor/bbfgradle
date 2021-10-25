// Original bug: KT-34140

    fun test() {
        val map = mapOf<String, Int>()
        val list = listOf<String>()

        val statuses: List<Int> = list.map {
            val int = map[it]
            checkNotNull(int)
            int //this int is non-null, however lambda is (String) -> Int?
        }
    }
