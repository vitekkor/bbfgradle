// Original bug: KT-22653

object Sync {
    var executing: Int = 0

    fun lock(f: Sync.() -> Int) {
        this.f()
    }
}

fun bar() {
    Sync.lock {
        println(executing)
        executing++
    }
}
