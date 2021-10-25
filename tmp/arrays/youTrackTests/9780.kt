// Original bug: KT-10834

package kt_tests.intersectionTypes

interface IBoss
interface IOther
class A1 : IBoss, IOther
class A2 : IBoss, IOther

fun Any.transmogrify() {
    println("Sorry, I can't transmogrify really good, but I'll try.")
}

fun IBoss.transmogrify() {
    println("Transmogrified like a boss.")
}

fun main(args: Array<String>) {
    listOf(A1(), A2()).forEach { it.transmogrify() }
}
