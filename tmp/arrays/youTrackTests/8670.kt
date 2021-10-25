// Original bug: KT-14255

interface Z<T> {
    fun test(p: T): T {
        return p
    }
}

open class ZImpl : Z<String> //same bridge here and

class ZImpl2 : ZImpl() {  //here!!!

    override fun test(p: String): String {
        return super.test(p)
    }
}

fun box(): String {
    return ZImpl2().test("OK")
}
