// Original bug: KT-2860

class MyMap(private val base: MutableMap<Int, String> = HashMap()) : MutableMap<Int, String> by base {
    override operator fun get(key: Int): String {
        return base.get(key) ?: "sorry"
    }
}
