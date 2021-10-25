// Original bug: KT-29669

fun main() {
   `doAThing) outer inner`()
   `doAThing) only`()
}

class Thing {
    private val myThing = 1

    fun thing() = myThing
}

typealias MyRunable = () -> Unit

fun doAThing(name: String, myRunable: MyRunable) {
    println("running $name")
    myRunable()
}

fun `doAThing) outer inner`() {
    val thing = Thing()

    doAThing("outer") { doAThing("inner") { thing.thing() } }
}

fun `doAThing) only`() {
    val thing = Thing()

    doAThing("only") { thing.thing() }
}
