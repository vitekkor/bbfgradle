// Original bug: KT-2740

package foo

class MyClass() {
    fun foo(): Int {
        fun innerFun(): Int {
            return mySimpleTest() // HERE
        }
        
        return innerFun()
    }

    fun mySimpleTest(): Int {
        return 5
    }
}

fun box() : Boolean {
    return MyClass().foo() == 5
}

