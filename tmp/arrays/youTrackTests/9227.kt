// Original bug: KT-15676

open class Parent {
    val list = mutableListOf<Int>()
}

class Child : Parent() {
    init {
        list.add(1)
        list += 2
    }
}

fun main(args: Array<String>) {
    println(Child().list)
}