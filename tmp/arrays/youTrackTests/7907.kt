// Original bug: KT-23274

suspend inline fun <reified T> ExtendMe.receive(): T = receive("") as T

class ExtendMe {
    suspend fun receive(s: String): Any {
        return Any()
    }
}
