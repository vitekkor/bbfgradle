// Original bug: KT-10313

class BehaviourSubject<T>(val value: T) {
    companion object {
        fun <T> create(value: T) = BehaviourSubject(value)
    }
}

fun use() {
    val sub = BehaviourSubject.create<Long>(-1)
    println(sub.value == 1L)
}
