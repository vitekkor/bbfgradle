// Original bug: KT-9746

class MyClass {
    fun myClassFunction() {
        TODO()
    }
    companion object {
        fun testBrokenWithInCompanionObject() {
            val myClass = MyClass()
            with(myClass) {
                myClassFunction() //Compiler error: Kotlin: Expression is inaccessible from a nested class 'Companion', use 'inner' keyword to make the class inner
            }
        }
    }
}
