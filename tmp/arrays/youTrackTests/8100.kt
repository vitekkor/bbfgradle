// Original bug: KT-13566

fun test1() {
    val map = mutableMapOf<Number, Any>()
    map[1] = 1
    map[1.0] = 2
    println(map)
}

fun test2() {
    val map = mutableMapOf<Any, Any>()
    map[1] = 1
    map[1.0] = 2
    map["1.0"] = 3
    map["1"] = 4
    map[1]
    println(map)
}

fun main(args: Array<String>) {
    test1()
    test2()
}
