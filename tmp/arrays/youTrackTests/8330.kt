// Original bug: KT-19946

/*with some modifier?*/ val foo = { println("foo") }

inline fun bar(f: () -> Unit) {
    f()
}

fun test1() {
  bar(foo)
}

fun test2() {
  bar({ println("foo") })
}
