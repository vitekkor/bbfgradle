// Original bug: KT-7774

inline fun foo(c: String? = null) {
    if (c != null) {
        println(c)
    }
}

fun test() {
    foo() // inlined bytecode has calls to IoPackage.println(), yet we statically know it won't be called
}
