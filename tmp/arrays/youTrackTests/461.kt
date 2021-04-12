// Original bug: KT-41570

open class Base {
    constructor() {
        onCreate()
    }

    constructor(o: Any) {
        onCreate()
    }

    open fun onCreate() {

    }
}

class Test : Base {
    private val nonNullVal = Any()

    constructor() : super()
    constructor(o: Any) : super(o)

    override fun onCreate() {
        println("Val: $nonNullVal")
    }
}

fun main() {
    Test()
}
