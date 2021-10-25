// Original bug: KT-9591

public fun String.testFun() {
        arrayOf("1", "2").forEach {
            object  {
                private val _delegate by lazy {
                    test()
                }
            }
        }
}

fun String.test() {}
