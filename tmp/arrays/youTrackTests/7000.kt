// Original bug: KT-24778

fun main(args: Array<String>) {
    with(View()) {
        listener = { modifiedListener { println("inner") } }
        notifyListener()
    }
}

class View {
    var listener: ((View) -> Unit)? = null

    fun notifyListener() {
        listener?.invoke(this)
    }
}

inline fun modifiedListener(crossinline listener: (View) -> Unit): (View) -> Unit {
    println("modified")

    return {
        println("outer")
        listener(it)
    }
}
