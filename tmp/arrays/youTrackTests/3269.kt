// Original bug: KT-36264

interface A
class B : A

val String.ext: A
    get() = TODO()

class Cls {
    fun take(arg: B) {}
    
    fun test(s: String) {
        if (s.ext is B)
            take(s.ext)  // resolved to member function in OI with error, resolved to external function in NI
    }
}

fun take(arg: Any) {}
