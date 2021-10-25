// Original bug: KT-9591

public fun testFun() {
    var z = "fail"
    inlineFun {
        object  {
            private val _delegate by lazy {
                z
            }
        }
    }
}

inline fun inlineFun(p: () -> Unit) {
    p()
}

fun main(args: Array<String>) {
    testFun()
} 
