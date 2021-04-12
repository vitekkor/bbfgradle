// Original bug: KT-25549

inline fun <reified T> String.parse(): List<T> =
    this.split(",").map {
        try {
            it as T
        } catch (e: ClassCastException) {
            error("fail")
        }
    }
