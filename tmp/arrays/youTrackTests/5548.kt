// Original bug: KT-23084

open class Outer {
    open class Nested : Outer()

    companion object {
        @JvmField
        val NESTED = Nested()
    }
}


fun main(args: Array<String>) {
    val t1 = Thread {
        Outer.NESTED
    }
    val t2 = Thread {
        Outer.Nested()
    }
    t1.start()
    t2.start()
}
