// Original bug: KT-10044

open class JClass() {
    fun test() {
    }
}

class Example : JClass() {
    //constructor() : super()

    private var obj: JClass? = null

    init {
        {
            obj?.test()
        }()
    }
}


fun main(args: Array<String>) {
    Example()
}
