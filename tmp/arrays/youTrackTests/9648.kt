// Original bug: KT-7265

fun test(b: Boolean) {
    val f = fun ():Int {return 1} // OK here
    val ff = if (b) fun (): Int {return 1} else fun (): Int {return 2} // Function declaration must have name here 
}
