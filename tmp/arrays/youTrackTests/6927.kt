// Original bug: KT-20792

fun m(a: List<String>, b: List<Class<Any>>) {
    a.forEach {
        val a2 = it //cannot inline a2
        b.forEach {
            println(a2.length)
        }
    }
}
