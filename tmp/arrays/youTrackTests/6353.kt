// Original bug: KT-30341

fun foo(list: List<Pair<String, String>>) {
    var index = 0
    for ((s1, s2) in list) {
        s1.substring(1)
        s2.substring(1)
        index++
    }
}
