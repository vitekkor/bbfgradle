// Original bug: KT-44733

import java.lang.ref.WeakReference

fun main() {
    var foo: Foo? = Foo()
    val fooRef = WeakReference(foo)

    println(foo)

    runBlock { foo = null }

    triggerAndWaitForGc()
    assert(fooRef.get() == null)
}

fun runBlock(block: () -> Unit) = block()

fun triggerAndWaitForGc() = repeat(5) {
    print("Triggering GC | ")
    val ref = WeakReference(listOf(1, 2, 3))
    while (ref.get() != null) {
        List(1000) { listOf(1, 2, 3, 4, 5) }
        System.gc()
        Thread.sleep(10)
    }
    println("GC triggered (probably)")
}

class Foo
