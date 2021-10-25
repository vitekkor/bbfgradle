// Original bug: KT-22295

class Test {
    private lateinit var prop: Any

    fun test() {
        if (!::prop.isInitialized) {
            synchronized(this) {
                if (!::prop.isInitialized) {
                    prop = Any()
                }
            }
        }
    }
}
