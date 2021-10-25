// Original bug: KT-23897

fun main(args: Array<String>) {
    A("one").test(A("two"))
}

class A(val a: String) {
    fun A.foo() {  
        print(this) // Prints "two", but "one" if receiver is removed.
    }
    
    fun test(other: A) {
        other.foo()
    }

    override fun toString() = a 
}
