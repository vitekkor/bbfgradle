// Original bug: KT-1048

import java.util.HashMap

public open class Java() {
    open  fun test() : Unit {
        var m : HashMap<Any?, Any?>? = HashMap()
        m?.put(1, 1)
    }
}
