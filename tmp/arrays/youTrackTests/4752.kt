// Original bug: KT-15921

class Z: HashMap<String, String>() {
    override fun getOrDefault(key: String, defaultValue: String): String {
        return super.getOrDefault(key, defaultValue)
    }
}

fun main(args: Array<String>) {
    Z()
}
