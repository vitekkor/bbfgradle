// Original bug: KT-17454

class A {
    private companion object B {
        // whatever
    }
}

fun main(args: Array<String>) {
    println(A.toString())
    println(A.to(1))
    println(A.apply {  })
    println(A.apply { this })
}
