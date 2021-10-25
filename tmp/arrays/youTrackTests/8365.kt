// Original bug: KT-17549

class A {
    fun unaryPlus() = this
    fun foo() {
        val a = A()
        //val a1 = +a //Error: Kotlin: 'operator' modifier is required on 'unaryPlus' in 'A'
    }
}
