// Original bug: KT-34324

fun bar(): String {
    return ::bar.name
}

fun function() {
    return repeat(15) {
        println(::function.name)
    }
}

val foo: () -> Unit = { println(::foo.name) }
