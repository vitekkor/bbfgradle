// Original bug: KT-10327

fun main(args: Array<String>) {
	val b = B("Hello", "World")
    val a = A.fromB(b)
    
    print("$a.value1 $a.value2")
}


class A() {
    var value1: String? = null
    var value2: String? = null
    
    companion object {
        //This fails with "Expression is inaccessible from a nested class 'Companion', use 'inner' keyword to make the class inner"
        fun fromB(b: B): A {
            return A().apply {
                value1 = b.value3
                value2 = b.value4
            }
        }
    }
}

class B(val value3: String, val value4: String) {}
