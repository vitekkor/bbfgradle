// Original bug: KT-22035

class MyThrowable : Throwable {
    constructor() : super()
    constructor(cause: Throwable?) : super(cause)
}

fun main(args: Array<String>) {
    println(MyThrowable().message)
    println(MyThrowable(cause = null).message)
}
// prints:
// undefined
// undefined
