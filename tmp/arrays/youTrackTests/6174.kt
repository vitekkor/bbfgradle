// Original bug: KT-31054

class Foo : AutoCloseable {
    override fun close() {
        println("foo is closed")
    }
}

fun main() {
    if (true) {
        Foo().use { foo -> 
            // do something with foo
        }
        // "foo is closed" here
    }
}
