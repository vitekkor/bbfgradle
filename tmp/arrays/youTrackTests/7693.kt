// Original bug: KT-22053

fun main(args: Array<String>) {
    println(MyThrowable(1))
}

class MyThrowable(message: String?) :  Throwable("through primary: " + message) {
    constructor() : this(message = "secondary") {
        println("ctor()")
    }
    constructor(i: Int) : this() {
        println("ctor(Int)")
    }
        
    init { println("init block") }
}
