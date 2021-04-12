// Original bug: KT-16375

package test

fun main(args: Array<String>) {
    `CompilationException)`()()()
}

fun `CompilationException)`(): () -> () -> Unit {
    return {
        {
            x()
        }
    }
}

fun x() {
    println("Hello, world!")
}
