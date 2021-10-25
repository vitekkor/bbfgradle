// Original bug: KT-22544

fun main(args: Array<String>) {
    println("a")
	myFun(0)
    println("b")
}

fun myFun(a: Int): Int {
    when (a) {
        MyObj.A, MyObj.B -> {
			println("AORB")
        }
        //else -> Unit // With this line, it works just fine
    }
    return 0
}

object MyObj {
	const val A = 0
	const val B = 1
}
