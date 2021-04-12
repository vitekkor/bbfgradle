// Original bug: KT-37455

open class BaseClass {
    @Deprecated("Use the other constructor!")
    constructor()
    constructor(x: String)
}

class DerivedClass: BaseClass {
    constructor()
    constructor(x:String): super(x)
}
