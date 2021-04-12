// Original bug: KT-10677

fun foo(arg1: HashMap<String, Any>) {

}

fun bar() {
    val baz = HashMap<String, Any>()
    when (baz) {
        is HashMap<*, *> -> {
            foo(baz)
        }
    }
}
