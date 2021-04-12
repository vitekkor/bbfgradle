// Original bug: KT-17454

class A {
    private companion object B {
        fun foo() {}
    }
}

fun main(args: Array<String>) {

    println(A.toString()) // OK
    //println(A.hashCode()) //Error:(10, 15) Kotlin: Cannot access 'B': it is private in 'A'
    //println(A.foo()) //Error:(11, 15) Kotlin: Cannot access 'B': it is private in 'A'
    println(A.to(1)) // OK
    //println(A to 1) // Error:(13, 13) Kotlin: Cannot access 'B': it is private in 'A'
    println(A.apply {  }) // OK
    println(A.apply { this }) // OK
    //println(A.apply { this.foo() }) // Error:(16, 28) Kotlin: Cannot access 'B': it is private in 'A'

}
