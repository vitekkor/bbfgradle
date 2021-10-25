// Original bug: KT-14221

data class D(val x: Int, val y: Int, val z: Int) 
fun foo() {
    val (x, y, z) = D(1, 2, 3)
    println(y + z) // x is not used, but we cannot do anything with it
}
