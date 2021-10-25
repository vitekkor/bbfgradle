// Original bug: KT-14243

interface Z<T> {
    fun test(p: T) {}
}

open class ZImpl : Z<String>

class ZImpl2 : ZImpl() {

    override fun test(p: String) {
        super.test(p) //INVOKESPECIAL ZImpl.test (Ljava/lang/Object;)V!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
}

fun main(args: Array<String>) {
    ZImpl2().test("123")
}
