// Original bug: KT-27857

class Foobar {
    inline fun forEach(action: () -> Unit): Unit {

    }

    fun test() {
        if (2 > 3) return
        forEach {
            if (3 > 5) return@forEach
            if (5 > 6) return
        }
        System.out.println(2)
    }
}
