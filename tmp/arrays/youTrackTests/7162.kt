// Original bug: KT-23512

interface TypeHolder<T>
fun <T> TypeHolder<T>.findById(): T? = TODO()
fun <R> expect(fn: () -> R) {}

fun callHolder(holder: TypeHolder<String>) {
    expect { holder.findById() }
}
