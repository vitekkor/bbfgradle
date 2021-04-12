// Original bug: KT-3023

import java.util.HashMap

class ClassName

class Foo() {
    private data class ClassData(
            val className: ClassName
    )

    fun foo() {
        ClassData(ClassName())
    }
}

fun main(args: Array<String>) {
    Foo().foo()
}
