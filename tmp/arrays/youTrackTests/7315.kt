// Original bug: KT-28053

open class Foo

interface Bar {
    val lazyLoadedString : String
}

class Test {
    private val foo by lazy {
        object : Foo(), Bar {
            override val lazyLoadedString = "Demo"
        }
    }
}
