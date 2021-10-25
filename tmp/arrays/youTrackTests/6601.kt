// Original bug: KT-27506

interface Named {
    val name: String?
}

abstract class KaptStubWorkAround: Named

class Product2 : KaptStubWorkAround {

    override var name: String? = null

    // Note: for some reason these constructors break the @JsonClass mechanism - among other things

    constructor(otherName: String) {
        this.name = otherName
    }
}
