// Original bug: KT-9913

inline fun foo(): (Int) -> Int = { it + 1}

inline fun bar(): (Int) -> Int = { it * 2}

inline fun compose(crossinline f: (Int) -> Int,
                   crossinline g: (Int) -> Int): (Int) -> Int {
    return { f(g(it)) }
}

fun test(f: (Int) -> Int) = f(42)

fun go() {
    test(compose(foo(), bar()))  // :(
}
