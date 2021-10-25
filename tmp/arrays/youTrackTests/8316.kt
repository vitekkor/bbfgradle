// Original bug: KT-5700

fun main(args: Array<String>) {
    A().module2()  
    print("000")
}

class A {

    inline public fun module2(description: String = ""): String {
        return ""
    }
}
