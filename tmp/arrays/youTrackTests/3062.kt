// Original bug: KT-37563

public open class MyExceptionSecondary : Exception {
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor() : super()
    constructor(message: String?) : super(message)   
    constructor(cause: Throwable?) : super(cause)  
}
