// Original bug: KT-23626

abstract class Base {
    init {
        prepare()
    }

    abstract fun prepare()

}

class TestInitString : Base() {

    private var field: String? = "Default"

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
