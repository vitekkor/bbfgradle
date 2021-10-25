// Original bug: KT-34475

fun foo() {}

fun bar() {
    val list = listOf("")
    with(list) { // false-positive "Redundant 'with' call
        print(size)
        foo() //comment this line and no inspections above - correct
    }
}
