// Original bug: KT-42965

fun process(options: Map<String, Int>, inputs: List<String>): List<Int> {
    val res = mutableListOf<Int>()
    var cur = -1
    for (str in inputs) {
        if (str.startsWith("-"))
            if (options.containsKey(str)) {
                if (cur == -1) cur = options[str]!!
            } else if (options.containsKey("+$str")) {
                if (cur == -1) cur = if (res.isEmpty()) -1 else // no warning
                    res.removeAt(res.size - 1)
                if (cur != -1) res.add(cur + str.length)
            }
    }
    return res
}
