// Original bug: KT-25435

fun indexer(): Map<Int, Int> = // Type mismatch in NI
        run {
            try {
                emptyMap()
            }
            catch(e: Throwable) {
                emptyMap()
            }
        }
