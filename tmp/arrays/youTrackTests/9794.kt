// Original bug: KT-7465


class A {
    fun f() : Unit {
    }
}

fun len1(v : A?) : Unit? = v?.f()
fun len2(v : A?) = v?.f()                   // this function always return Unit but it actually could return null
fun lenOrOne(v : A?) = v?.f() ?: 1   // here we should have no warning as v is nullable

fun main(args: Array<String>) {
    println("len1(A) = " + len1(A()))
    println("len1(null) = " + len1(null))

    println()

    println("len2(A) = " + len2(A()))
    println("len2(null) = " + len2(null))     // hmmm... null is expected here but got Unit

    println()

    println("lenOrOne(A) = " + lenOrOne(A())) 
    println("lenOrOne(null) = " + lenOrOne(null))
}
