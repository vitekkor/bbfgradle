// Original bug: KT-29996

// function compiled with -jvm-target: 1.8
inline fun inlineFun(p: () -> Unit) {
    p()
}

// function compiled with -jvm-target: 1.6
fun test() {
    inlineFun {} // EROR
}
