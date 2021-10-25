// Original bug: KT-44198

package compilerBug.demo

fun main(args: Array<String>) {
    AnonymousClassInLambda().run()
}

class AnonymousClassInLambda() {
    fun run() {
        val foo = listOf("hello", "world")
        val threads = (1..10).map {
            object: Runnable {
                override fun run() {
                    println("foo has size ${foo.size}")
                }
            }
        }
        threads.forEach { it.run() }
    }
}
