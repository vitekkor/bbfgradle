// Original bug: KT-17991

class K {

    var prop: String? = null

    fun setProp(arg: String?): Int {
        TODO()
    }
}

fun main() {
    val str: Int = K().setProp(null)
    K().prop = null
}
