// Original bug: KT-27378

class A {
    companion object { // "Cannot perform refactoring. Move declaration is not supported for companion objects"
        fun bar() {
            println("Hello")
        }
    }
}

fun main(args: Array<String>) {
    A.bar()
}
