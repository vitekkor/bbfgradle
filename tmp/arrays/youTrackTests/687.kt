// Original bug: KT-43723

@Suppress("MemberVisibilityCanBePrivate", "unused")
inline class ModInt(val x: Int) {
    companion object {
        private const val mod = 1e9.toInt() + 7
    }

    operator fun plus(k: ModInt) = ModInt((x + k.x) % mod)
    operator fun times(k: ModInt) = ModInt((x.toLong() * k.x.toLong() % mod).toInt())
    override fun toString() = x.toString()
}
