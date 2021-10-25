// Original bug: KT-9718

interface A {
    @Deprecated("")
    fun deprecatedFunction()

    @Deprecated("")
    val deprecatedProperty: String
}

class C : A {
    fun z() {
        deprecatedFunction() // NOT REPORTED
        deprecatedProperty // NOT REPORTED
    }

    override fun deprecatedFunction() {
    }

    override val deprecatedProperty: String
        get() = ""
}

fun main(args: Array<String>) {
    C().deprecatedFunction()   // NOT REPORTED
    C().deprecatedProperty   // NOT REPORTED
    (C() as A).deprecatedFunction()  // reported as expected
    (C() as A).deprecatedProperty  // reported as expected
}
