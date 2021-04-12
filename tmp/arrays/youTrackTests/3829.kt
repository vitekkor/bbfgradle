// Original bug: KT-37982

fun foo(list: List<String>) {
    fun localFun() {
        var prev: String? = null
        for (s in list) {
            if (prev != null) {
                println(prev.length)
            }
            prev = s
        }
    }
}
