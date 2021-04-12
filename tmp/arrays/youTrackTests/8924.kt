// Original bug: KT-17749



fun checkInheritance() {
    val widget = Son()
    widget.child.trigger()
}

private typealias Listener = () -> Unit


private class Observable {
    private val listeners = mutableListOf<Listener>()
    fun on(listener: Listener) = listeners.add(listener)
    fun fire() = listeners.forEach { it() }
}

private open class Basic {

    val observable = Observable()

    open fun trigger() {
        observable.fire()
    }
}

private class Son : Father() {

    val child = Basic().also { add(it) }

    private fun doSomethingPrivate() {
        throw Exception("this must not be called!")
    }
}


private open class Father : Basic() {

    private val onChildEvent = { trigger(); doSomethingPrivate() }

    fun add(child: Basic) {
        child.observable.on(onChildEvent)
    }

    private fun doSomethingPrivate() {
        println("this is expected to be called!")
    }

}

