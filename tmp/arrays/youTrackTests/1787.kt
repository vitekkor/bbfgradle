// Original bug: KT-43801

fun <T> underlying(a: IC): T = bar(a, object : IFace<IC, T> {
    override fun call(ic: IC): T = ic.value as T
})

interface IFace<T, R> {
    fun call(ic: T): R
}

fun <T, R> bar(value: T, f: IFace<T, R>): R {
    return f.call(value)
}
inline class IC(val value: String) {
    fun <T> dispatchValue(): T = value as T
}
