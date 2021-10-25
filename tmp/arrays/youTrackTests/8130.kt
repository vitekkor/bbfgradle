// Original bug: KT-21776

fun main(args: Array<String>) {
    println(val1) //<- prints undefined in Js, "ok" inside JVM
}
val val1 = fun1()
fun fun1()= fun2()
val val2 = "ok"
fun fun2()  = val2
