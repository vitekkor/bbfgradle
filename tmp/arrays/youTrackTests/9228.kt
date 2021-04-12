// Original bug: KT-15676

interface Listable {
    val list: MutableList<Int>
}

open class Parent : Listable {
    override val list = mutableListOf<Int>()
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
