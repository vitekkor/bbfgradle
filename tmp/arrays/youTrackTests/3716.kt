// Original bug: KT-34696

package packageName

import packageName.MyEnum.*

enum class MyEnum {
    A, B, C, D, E
}

fun main() {
    println(A)
    println(A)
    println(B)
    println(C)

    // here: MyEnum shouldn't be highlighted as 'Redundant qualified name'
    MyEnum.valueOf("A")
    for (value in MyEnum.values()) {
        println(value)
    }
}
