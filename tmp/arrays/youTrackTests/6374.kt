// Original bug: KT-18134

class A
fun <T>foo(t: T) {}
fun main(args: Array<String>) {
    val v = if (true) A() else 1     // Here is warning "Conditional branch result of ... is implicitly cast to Any"
    print("${if (true) A() else 1}") // And here is too
    
    foo(if (true) A() else 1)    // But here is not
    print(if (true) A() else 1)  // But here is not either
    1 to if (true) A() else 1
}
