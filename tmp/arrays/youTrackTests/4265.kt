// Original bug: KT-32430

fun <T> readAction(cl: () -> T): T {
    return cl()
}
class A {
    private val v: MutableSet<Int>? = readAction {
        var map: MutableSet<Int>? = null
        for (i in 1..10) {
            if (i % 2 == 0) {
                if (map == null) map = mutableSetOf()
                map.add(i)
            }
        }
        map
    }
}
