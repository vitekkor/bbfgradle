// Original bug: KT-6382

fun main(args: Array<String>) {
    A().run()
}

public class A {
    fun run() {
        with ("abc") {
            show()
        }
    }

    private fun String.show(p: Boolean = false) {
        println(getName())
        print(this)
    }

    private fun getName() = "A"
}
