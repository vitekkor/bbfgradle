// Original bug: KT-15852

fun main(args: Array<String>) {
    val iAmNotNull: C = d
    println("iAmNotNull is $iAmNotNull")
}

val d: C = a.b    // Swapping these two lines fixes it, of course
val c = C()       // ...

class C

object a {
    val b = c
}
