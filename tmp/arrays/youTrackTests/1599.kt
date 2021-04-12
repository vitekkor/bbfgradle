// Original bug: KT-16244

data class Reference (
        var ref: Reference?
)

fun main(args: Array<String>) {
    val refTest = Reference(null)
    refTest.ref = refTest
    refTest.toString() // or refTest.hashCode()
}
