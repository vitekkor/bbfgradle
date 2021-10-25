// Original bug: KT-13817

inline fun foo(get: () -> String) = get()
inline fun bar(set: (String) -> Unit) = set("")

var value = ""

fun test() {
    foo(::value.getter)
    bar(::value.setter)
}
