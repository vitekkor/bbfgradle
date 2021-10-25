// Original bug: KT-44739

interface Num<A> {
    operator fun A.plus(other: A): A
    val zero: A
}

fun <A> doubleIfNotNullElseZero(numInstance: Num<A>, x: A?): A {
    with(numInstance) {
        return if (x != null) {
            x + x
        } else {
            zero
        }
    }
}
