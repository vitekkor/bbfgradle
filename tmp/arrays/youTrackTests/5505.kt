// Original bug: KT-27324

package test

class Host {
    val delegated by Delegate()
    
    init {
        println(delegated) // <-- implicit leaking this, prints 'null'
    }
    
    val unintialized = "OK"
}

class Delegate {
    operator fun getValue(thisRef: Host, prop: Any?) = thisRef.unintialized
}

fun main(args: Array<String>) {
    Host()
}
