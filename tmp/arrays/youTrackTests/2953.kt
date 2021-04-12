// Original bug: KT-28546

inline fun takeWhileSize(initialSize: Int = 1, block: (String) -> Int) {
    val current = "PARAM"

    try {
            val before = 123

            if (before >= initialSize) {
                try {
                    block(current)
                } finally {
                    "INNER FINALLY"
                }
            } else {
                "ELSE"
            }

        //} while (size > 0)
    } finally {
        "OUTER FINALLY"
    }
}
