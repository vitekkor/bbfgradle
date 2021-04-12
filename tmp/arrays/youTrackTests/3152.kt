// Original bug: KT-39466

enum class Boo {
    BAZ,
    BAM {
        override val foo = "bar"
    };

    open val foo: String
        get() = error("no foo set for ${javaClass.simpleName}.${name}")
}
