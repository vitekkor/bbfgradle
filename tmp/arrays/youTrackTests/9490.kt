// Original bug: KT-10824

class A
private fun foo(a: A?, aOther: A?): A {
    // Should be smart cast on if or newA
    return if (a == null) {
        A()
    }
    else {
        var newA = aOther
        if (newA == null) {
            newA = A()
        }
        // Error: type mismatch
        newA
    }
}
