// Original bug: KT-16411

object Foo {
    class Requester(val dealToBeOffered: String)
}

inline fun <A, reified R> startFlow(
        flowConstructor: (A) -> R
) {}


fun main(args: Array<String>) {
    startFlow(Foo::Requester)
}
