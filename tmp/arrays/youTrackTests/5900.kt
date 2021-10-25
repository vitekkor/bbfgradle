// Original bug: KT-31963

open class A(open val name: String)
class B(name: String) : A(name) {
    override val name = super.name.transform()
}

private fun String.transform() = "$this updated"

fun main() {
    println(B("initial").name) // initial updated
}
