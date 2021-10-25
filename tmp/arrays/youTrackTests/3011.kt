// Original bug: KT-37120

interface A {}
class B() {}

val case1 = object : A {
    inner class Child(property: B) : Base(property) { //note that in dump there is msg `Symbol not found, for Base`
        fun Base.zoo() {
            val x = property //UNRESOLVED_REFERENCE
        }

        fun foo() {
            baseFun() //UNRESOLVED_REFERENCE
            val x = property //UNRESOLVED_REFERENCE
            zoo() //UNRESOLVED_REFERENCE
            hoo()
        }
    }
    fun Child.voo() {
        val x = property //UNRESOLVED_REFERENCE
    }

    fun Base.hoo() {
        val x = property //UNRESOLVED_REFERENCE
    }

    open inner class Base(/*protected*/ val property: B) {
        fun baseFun() {}
    }

    fun caseForBase() {
        val base = Base(B())
        /*member of Base*/
        base.baseFun() //UNRESOLVED_REFERENCE
        base.property //UNRESOLVED_REFERENCE
        /*extensions*/
        base.hoo()
    }

    fun caseForChild() {
        val child = Child(B())
        /*member of Base*/
        child.baseFun() //UNRESOLVED_REFERENCE
        child.property //UNRESOLVED_REFERENCE
        /*member of Child*/
        child.foo() //UNRESOLVED_REFERENCE
        /*extensions*/
        child.hoo()
        child.voo()
    }
}
