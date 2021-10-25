// Original bug: KT-25790

object Timer {
    fun now(): Long = System.currentTimeMillis()

    fun foo(): () -> Long {
        val start = now()
        return { now() - start }
    }

    val system: () -> () -> Long = ::foo
}
