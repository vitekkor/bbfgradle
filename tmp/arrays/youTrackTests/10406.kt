// Original bug: KT-3584

fun main(args: Array<String>) {
    val s = "captured";

    class A(val param: String = "default") {

        val s2 = s + param
    }

    A().s2
    A("test").s2
}
