// Original bug: KT-7530

import java.util.ArrayList

public open class Test {
    private var _something: ArrayList<Int>? = null

    public fun test() {
        _something = null
        println("Something: " + (_something == null)) // "Something: null"
        if (_something != null) {
            println("Not Null") // "Not Null"
        }
    }
}

fun main(args: Array<String>) {
    Test().test()
}
