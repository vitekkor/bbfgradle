// Original bug: KT-31182

class Foo<T>
@Deprecated(message = "Use collect instead", level = DeprecationLevel.WARNING, replaceWith = ReplaceWith("this.collect(action)"))
suspend inline fun <T> Foo<T>.consume(action: (T) -> Unit) {}
suspend inline fun <T> Foo<T>.collect(action: (T) -> Unit) {}

public suspend fun foo(f: Foo<Int>) {
    f.consume {
        println()
    }

}
