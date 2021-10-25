// Original bug: KT-41034

class A(val b: B)

class B
operator fun B?.plusAssign(x: Int) {
    println("$this d")
}

fun foo(a: A?) {
    // Resolved sucessfully, operator is actually invoked both on null and not-null receivers
    a?.b += 1
}
