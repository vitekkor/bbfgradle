// Original bug: KT-37712

fun interface FunIntf {
    fun String.foo() //with receiver
}

val fi = FunIntf {
    this // does not refer to anything, should be String
    length // does not work
}

//this works, however
val fi2 = object : FunIntf {
    override fun String.foo() {
        println(length)
    }
}

