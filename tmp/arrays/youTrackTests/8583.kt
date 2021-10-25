// Original bug: KT-20287

fun main(args: Array<String>) {
    println(a() == null)
    a() ?: println("error")
    println(b() == null)
    b() ?: println("error")
}

fun a() {}
fun b(): Unit {}
fun c(): Unit { return Unit }
