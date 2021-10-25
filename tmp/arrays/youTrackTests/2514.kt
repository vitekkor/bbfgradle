// Original bug: KT-36846

fun foo(x : String) : String {
    assert("abz]".hashCode() == "aby|".hashCode())

    when (x) {
        "abz]" -> return "abz"

        "ghi"  -> return "ghi"
        "aby|" -> return "aby"
        "abz]" -> return "fail"
    }

    return "other"
}
