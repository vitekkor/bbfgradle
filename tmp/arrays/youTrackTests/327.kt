// Original bug: KT-45187

fun box(): String =
    object : A<Void, Void>() {
        override fun f(vararg params: Void): Void? = null
    }.execute()

abstract class A<Params, Result> {
    protected abstract fun f(vararg params: Params): Result?

    fun execute(vararg params: Params): String =
        if (f(*params) == null) "OK" else "Fail"
}
