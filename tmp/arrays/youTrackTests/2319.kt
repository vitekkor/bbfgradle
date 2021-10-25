// Original bug: KT-25307

suspend fun excs() { throw Exception("!!!") }
suspend fun fff() {}
suspend fun bars(): Int {
    var i = 0
    while (i < 3) {
        ++i
        try {
            try {
                fff()
                return i
            } finally  {
                excs()
            }
        } catch (x: Exception) {
            continue
        }
    }
    
    return -2
}
