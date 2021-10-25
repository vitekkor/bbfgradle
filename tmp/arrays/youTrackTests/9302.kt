// Original bug: KT-14810

var a = 0

var b: Int
    get() = a
    set(c: Int) {
        a = c
    }

fun box(): String {
    if (b++ != 0) return "fail1: $b"
    if (++b != 2) return "fail2: $b"
    if (--b != 1) return "fail3: $b"
    if (b-- != 1) return "fail4: $b"

    b += 10
    if (b != 10) return "fail5: $b"
    b *= 10
    if (b != 100) return "fail6: $b"
    b /= 5
    if (b != 20) return "fail7: $b"
    b -= 20
    if (b != 0) return "fail8: $b"
    b = 7
    b %= 5
    if (b != 2) return "fail9: $b"

    return "OK"
}
