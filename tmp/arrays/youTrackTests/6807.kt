// Original bug: KT-7491

class A(val y: String)

fun main(args: Array<String>) {
    val x: A? = A("")
    val z = x?.y ?: return
    x.y // Error: Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type A?
}
