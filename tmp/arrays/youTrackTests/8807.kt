// Original bug: KT-18746

class A(initialBigObject: Any) {
    private val someContainer = object {
        var ref = initialBigObject
    }

    fun updateRef(nextBigObject: Any) {
        someContainer.ref = nextBigObject
    }
}

fun main(args: Array<String>) {
    val a = A(object { val name = "First" })
    a.updateRef(object { val name = "Second" })
}
