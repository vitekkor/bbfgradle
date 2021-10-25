// Original bug: KT-15488

inline fun <reified T> getInstance(): T = TODO()

class Factory<T>
fun <T> factory(f: () -> T): Factory<T> = TODO()

fun main(args: Array<String>) {
    val v: Factory<String> = factory { getInstance() }
//                                     ^^^^^^^^^^^
//                                     Type inference failed:
//                                     Not enough information to infer parameter T
}
