// Original bug: KT-25787

class Presenter(private val token: String) {
    fun load(): String {
        var result = "Fail"
        subscribeLambda {
            result = token
        }
        return result
    }
}

inline fun subscribeLambda(lambda: (Int) -> Unit) {
    lambda.id()(42)
}

inline fun ((Int) -> Unit).id(): (Int) -> Unit = { this.invoke(it) }

fun box(): String = Presenter("OK").load()
