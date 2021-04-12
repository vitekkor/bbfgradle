// Original bug: KT-37837

class Cache {
    operator fun contains(element: Long): Boolean = TODO()
    suspend operator fun plusAssign(element: Long): Unit = TODO()
}
val cache = Cache()

suspend fun workAround() {
    cache += 1L
}

suspend fun fails() {
    if (1L !in cache) workAround()
}
