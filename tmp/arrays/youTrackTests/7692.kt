// Original bug: KT-22053

fun main(args: Array<String>) {
	println(MyThrowable())
}

class MyThrowable(message: String?) :  Throwable("through primary: " + message) {
    constructor() : this(message = "secondary") {
    }
    	
    init { println("init block") }
}
// actual output
// Error: secondary

// expected output
// init block
// MyThrowable: through primary: secondary
