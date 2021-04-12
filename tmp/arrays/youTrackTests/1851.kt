// Original bug: KT-39437

inline class VerifyErrorOne(val ignored: String) {
    suspend fun a() = ignored
    suspend fun b() {
        val a = a()
    }
}

fun main() {
    VerifyErrorOne("hi")
}
