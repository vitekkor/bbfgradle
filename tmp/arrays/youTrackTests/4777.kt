// Original bug: KT-23064

fun main(args: Array<String>) {
    val data = SomeData()
    val numbers = arrayListOf(1, 2, 3, 4, 5)

    data.let { it: SomeData -> someExtraFun(it) } // breakpoint on lambda { it: SomeData -> someExtraFun(it) }
    data.apply { println(this) } // breakpoint on lambda { println(this) }
    numbers.run { filter { (it == 5) } } // breakpoint on lambda { (it == 5) }
    with(data) { println(this) } // breakpoint on lambda { println(this) }
    data.also { println(it) } // breakpoint on lambda { println(it) }
}

class SomeData

fun someExtraFun(d: SomeData) {
    println(d)
}
