// Original bug: KT-44303


class Api

fun Api.api(block: Runnable) {
    println("> Api.api")
    block.run()
}

val Api.api: Api
    get() = this

operator fun Api.invoke(block: () -> Unit) {
    println("> Api.invoke")
    block()
}

fun Api.script() {
    api { // should invoke Api.api(block: Runnable)
    }
}

fun main() {
    Api().script()
}
