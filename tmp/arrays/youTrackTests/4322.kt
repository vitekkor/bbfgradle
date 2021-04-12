// Original bug: KT-36707

class CaseBase2() {

    companion object foo {                          //(0)
        val foo = { println("val foo") }            //(2)
        operator fun invoke() { println("invoke") } //(3)
        
    }

    fun test() {
        foo.foo //(0).(2) foo - redundant receiver
        foo.foo() //(0).(2) foo - redundant receiver
        //accept idea's suggestion to remove redundant companion receiver
        foo   //resolved to (0)
        foo() // resolved to (3)
    }
}
