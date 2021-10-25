// Original bug: KT-15863

fun main(args: Array<String>) {
    val f: String.(Int) -> Unit = String::a
    f("string", 42)
    "string".f(42)
}


fun String.a(param: Int) {
    println("rcvr: $this, param: $param")
    //if (param != 42) throw Exception()
}
