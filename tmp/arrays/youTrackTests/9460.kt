// Original bug: KT-12808

fun test1() {
    val list = mutableListOf<(Int) -> Unit>()

    var a = 1
    list += { m -> 
             println(m) 
             a = 2
            }
    
    for(f in list) f(1)
}

fun main(args: Array<String>) {
    test1()
}
