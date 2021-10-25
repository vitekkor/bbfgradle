// Original bug: KT-34695

package packageName

enum class MyEnum {
    A, B, C, D, E
}

fun main() {
    // Here: 'Import members from 'packageName.MyEnum'
    // Alternative intentions could be: 'Add import for 'packageName.MyEnum.*' or 'Add imports for all 'packageName.MyEnum' constants'
    println(MyEnum.A)
    println(MyEnum.A)
    println(MyEnum.B)
    println(MyEnum.C)
    println(MyEnum.D)
    println(MyEnum.E)

    // these usages shouldn't be updated:
    MyEnum.valueOf("A")
    for (value in MyEnum.values()) {
        println(value)
    }
}
