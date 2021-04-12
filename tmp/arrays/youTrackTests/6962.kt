// Original bug: KT-22295

fun acceptLambda(lambda: () -> Unit) {}

class SyncMemRef {
    private lateinit var prop: Any

    fun callLambda() {
        acceptLambda {
//            ::prop.isInitialized // Uncomment.
        }
    }
}
