// Original bug: KT-597

fun <T> Array<T>?.get(i: Int) : T {
    if (this != null)
        return this.get(i) // <- inferred type is Any? but &T was excepted
    else throw NullPointerException()
}
