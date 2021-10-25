// Original bug: KT-24209

fun main(args: Array<String>) {
    Holder?.value() // expected Holder?.value?.invoke()
}

interface Interface

operator fun Interface.invoke() = Unit

class Class : Interface

object Holder {
    val value = Class()
}
