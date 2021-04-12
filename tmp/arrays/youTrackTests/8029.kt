// Original bug: KT-22128

/*internal*/ abstract class Base { // The `internal` keyword is commented out due to https://youtrack.jetbrains.com/issue/KT-22127
    init {
        prepare()
    }

    abstract fun prepare()

}

class TestInitString : Base() {

    private var field: String? = null


    fun dump() {
        println(field)
    }

    override fun prepare() {
        field = "Hello, bug!"
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val obj = TestInitString()
            obj.dump()
        }
    }

}
