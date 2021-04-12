// Original bug: KT-10044

open class JClass(ctx: Any)

class Example : JClass {
    constructor(ctx: Any) : super(ctx)

    private var obj: Any? = null

    init {
        {
            obj?.let {  }
        }()
    }
}

fun main(args: Array<String>) {
    Example("13")
}
