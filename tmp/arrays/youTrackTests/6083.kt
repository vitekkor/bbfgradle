// Original bug: KT-26104

class Abcd(i: Int)
class Vb(n: String) {
    var abcd: Abcd? = null
}

val vb = Vb("")
val fooBar = vb.also { // convert `also` to `apply`
    it.abcd?.apply {
        println("${it.abcd}")
    }
}
