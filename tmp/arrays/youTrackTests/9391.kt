// Original bug: KT-12995

var state = 0

fun main(args: Array<String>) {
    (state++)::class
    (state++)::class.java
    (state++)::toString.name
    assert(state == 3) { "state = $state" }
}
