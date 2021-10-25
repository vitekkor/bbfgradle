// Original bug: KT-8120

fun main() {

    var checked = false

    class InnerClass() {
        fun check() {
            if (!checked) println("InnerClass.check()")
        }
    }

    class MyClass() {
        val innerInstance = InnerClass()

        fun run() {
            println("MyClass.run()")
        }
    }

    MyClass().run()
}
