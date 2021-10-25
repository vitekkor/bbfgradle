// Original bug: KT-36266

fun test(i: Int) {
    var fn: () -> String

    if (i == 1) {
        fn = { "foo" }
    } else if (i == 2) {
        fn = { "bar" }
    } else {
        fn = { "baz" }
    }
}
