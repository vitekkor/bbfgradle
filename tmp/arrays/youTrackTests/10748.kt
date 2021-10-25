// Original bug: KT-1611

package test;

fun main(args : Array<String>) {
    Foo().doBar("qwe")
}

class Foo() {
    val bar : (str : String) -> String = { it }

    fun doBar(str : String) {
        bar(str);
    }
}
