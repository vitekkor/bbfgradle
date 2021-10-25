// Original bug: KT-36508

class Receiver(val x: Int = 0)
class Argument(val y: Int = 1)

val test = mapOf(
    "".to<String, Receiver.(Argument) -> Unit> { // "Replace 'to' with infix form" inspection
        println(x)
        println(it.y)
    })
