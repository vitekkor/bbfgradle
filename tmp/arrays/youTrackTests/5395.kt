// Original bug: KT-33649

class Test {
    var x: Any
        get() = field
        set(value) {}

    constructor() {
        x = Any()
    }
}

fun main() {
    println(Test().x.toString())
}
