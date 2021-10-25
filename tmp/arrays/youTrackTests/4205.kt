// Original bug: KT-13725

interface Expression<V>
infix fun <V> Expression<V>.within(array: Expression<Array<out V>>): Expression<V> = TODO()
fun <V> literal(value: V): Expression<V> = TODO()
val id: Expression<String> = TODO()
fun f() {
    id within literal(arrayOf("")) // fails
    id.within(literal(arrayOf(""))) // fails
    id.within<String>(literal(arrayOf(""))) // ok, with a warning to remove explicit type argument
}
