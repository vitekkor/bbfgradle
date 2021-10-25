// Original bug: KT-33597

open class A(init: A.() -> Unit) {
    val prop: String = ""
}

object B : A({
})

object C : A({
    fun foo() = B.prop.toString() // Redundant qualifier name
})
