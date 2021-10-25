// Original bug: KT-14148

open class Base(o: Any)

open class Outer {
    open inner class Inner

    inner class JavacBug : Base(object : Outer.Inner() {})
}

fun main(args: Array<String>) {
    Outer().JavacBug()
}
