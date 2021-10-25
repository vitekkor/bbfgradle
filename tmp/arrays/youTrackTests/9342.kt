// Original bug: KT-13796

fun print(vararg elements : Any?) = elements.joinTo(System.out)
fun print(source: Iterable<*>) = source.joinTo(System.out)

fun println(vararg elements : Any?) = elements.joinTo(System.out, postfix = "\n")
fun println(source: Iterable<*>) = source.joinTo(System.out, postfix = "\n")
