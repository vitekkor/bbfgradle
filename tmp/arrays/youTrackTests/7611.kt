// Original bug: KT-22649

var result = ""

inline var apx:Int
    get() = 0
    set(value) { result = if (value == 1) "OK" else "fail" }


fun test(s: Int?) {
    apx = s!!
}

fun main(args: Array<String>) {
    test(1)
}
