// Original bug: KT-10397

abstract class Outer {
    inner class Inner<R>
    fun <R> foo(): Inner<R>? = null
}

fun box(): String {
    Outer::class.java.getDeclaredMethods().single { it.name == "foo" }.toGenericString()

    return "OK"
}
