// Original bug: KT-12606

class X  {
    operator fun component1() : String = TODO()
}
fun getX() : X = TODO()
fun x() {
    val (a: Any) = getX() 
    a // type for a is String instead of Any
}
