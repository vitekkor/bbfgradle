// Original bug: KT-8668

fun main(args: Array<String>) {
    Person("Homer Simpson").sayName()
}

class Person(val name: String) {

    fun sayName() = doSayName { println(name) }

    inline fun <R> doSayName(crossinline call: () -> R): Unit {

        nestedSayName1 { nestedSayName2 { call() } }
    }

    fun nestedSayName1(call: () -> Unit) {
        call()
    }

    inline fun nestedSayName2(call: () -> Unit) {
        call()
    }
}
