// Original bug: KT-39466

enum class Boo(private val fooDelegate: String? = null) {
    BAZ,
    BAM(fooDelegate = "bar");

    val foo: String
        get() = fooDelegate ?: error("no foo set for ${javaClass.simpleName}.${name}")
}
