// Original bug: KT-23284

object WithInvoke {
    operator fun invoke() {
        println("I'm fine!")
    }
}

inline val <reified T> T.foo: WithInvoke
    get() {
        println(this is T)
        return WithInvoke
    }

fun main(args: Array<String>) {
    println("abc".foo.invoke())    // this works
    println("abc".foo())           // this throws an exception
}
