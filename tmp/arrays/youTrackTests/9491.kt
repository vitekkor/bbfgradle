// Original bug: KT-10824

class A
private fun foo(a: A?, aOther: A?): A {
    // Should be smart cast on if or aOther
    return if (a == null) {
        A()
    }
    else {
        if (aOther == null) {
            return A()
        }
        // Error: type mismatch
        aOther
    }
}
