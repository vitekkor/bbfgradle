// Original bug: KT-30991

class RequestHolder  {
    suspend fun request(data: String): String = TODO()
}

fun main() {
    val request: suspend (String) -> String
            = RequestHolder()::request
}
