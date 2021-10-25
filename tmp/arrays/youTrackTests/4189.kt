// Original bug: KT-36002


fun <A, B> get(first: A?, second: B?): Any {
    return if (first == null) {
        if (second == null) {
            throw Exception()
        } else second
    } else first
}

fun main() {
    get(1, 2)
}

