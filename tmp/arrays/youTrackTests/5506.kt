// Original bug: KT-27323

package test

class Host {
    val delegated by DelegateFactory() // <-- implicit leaking this in 'provideDelegate' convention
    val unintialized = "OK"
}

class DelegateFactory {
    operator fun provideDelegate(thisRef: Host, prop: Any?) =
        Delegate(thisRef.unintialized)
}

class Delegate<T>(val value: T) {
    operator fun getValue(thisRef: Host, prop: Any?) = value
}

fun main(args: Array<String>) {
    println(Host().delegated)   // null
}
