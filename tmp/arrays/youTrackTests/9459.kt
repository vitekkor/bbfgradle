// Original bug: KT-12807

fun test1() {
    val list = mutableListOf<(Int) -> Unit>()

    list += { m -> println(m) }
    
    for(f in list) f(1)
}

fun main(args: Array<String>) {
    test1()
}
