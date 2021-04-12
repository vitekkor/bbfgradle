// Original bug: KT-25343

import kotlin.reflect.*

// var out by atomicRef(listOf<String>()) // works
fun main(args: Array<String>) {
	var out by atomicRef(listOf<String>()) // fails
	val obj = object {
        init {
            println(out)
        }
    }
}

class atomicRef<T>(var initial: T) {
	val value = java.util.concurrent.atomic.AtomicReference<T?>(initial)

	inline operator fun getValue(obj: Any?, property: KProperty<*>): T {
		@Suppress("UNCHECKED_CAST")
		return this.value.get() as T
	}

	inline operator fun setValue(obj: Any?, property: KProperty<*>, v: T) {
		this.value.set(v)
	}
}
