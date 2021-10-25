// Original bug: KT-42003

inline fun <reified T> build(): T = // fake impl to make it runnable
    when (T::class) {
        Any::class -> Any() as T
        String::class -> "random string" as T
        else -> error("Unsupported type ${T::class}")
    }

inline fun receive(name: String, value: Any?) = // fake impl to make it runnable
    println("$name = $value")

fun main() {
    receive("empty", if (true) "" else build())
    receive("built", if (false) "" else build())
    receive("empty2", if (true) "" else build<String>())
    receive("explicit", if (false) "" else build<String>())
}
