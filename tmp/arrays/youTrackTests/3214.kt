// Original bug: KT-39265

open class Base<L> {

    private var listener: L? = null

    abstract class Listener<T> {
        abstract fun onChange(value: T);
    }

    fun setListener(listener: L) {
        this.listener = listener
    }
}

class MyClass : Base<MyClass.MyListener>() {

    abstract class MyListener: Listener<Float>()
}
