// Original bug: KT-40957

data class A1(val a: Int, val b: CharSequence){
    fun copy(a: Int, b: String) : Any = TODO() //(2)
}

fun case(x: A1) {
    val c = x.copy(1, "") // to (2)
}
