// Original bug: KT-30728

inline fun inlineMe(crossinline y: () -> Unit) =
        object {
            inline fun run() { y() }
        }.run()

fun main() {
    inlineMe { println(1) }
}
