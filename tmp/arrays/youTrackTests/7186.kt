// Original bug: KT-28566

fun main() {
    var t: Boolean? = true
    t!!
    val f = {
        val t: Int? = 10
    }
    println(t) // {Boolean & Boolean?}, smart cast to Boolean
}
