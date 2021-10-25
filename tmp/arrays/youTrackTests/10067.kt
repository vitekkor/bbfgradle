// Original bug: KT-6990

fun main(args: Array<String>) {
    print(inlineMeIfYouCan<StringBuilder>())
}

public inline fun <reified T : Any> inlineMeIfYouCan(): String? =
        {
            f {
                T::class.java.getName()
            }
        }()

inline fun f(x: () -> String) = x()
