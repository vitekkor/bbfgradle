// Original bug: KT-15449

class A  {
    val full_vehicle_history =
            linearLayout2 {
                {
                    apply2 {
                        this@linearLayout2::hashCode
                    }
                }
            }

    public fun <T> T.apply2(block: T.() -> Unit) {
        block()
    }
}


inline fun A.linearLayout2(init: X.() -> Unit) {
    return X().init()
}

class X
