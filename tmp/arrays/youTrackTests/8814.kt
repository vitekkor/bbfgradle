// Original bug: KT-18331

inline fun foo( f : () -> Unit ) {
    a@ for (i in 1..2) {
        f()
        break
    }
}


fun main(args: Array<String>) {

    foo {foo {foo {foo {foo {foo {foo {foo {
        foo {foo {foo {foo {foo {foo {foo {foo {
            foo {foo {foo {foo {foo {foo {foo {foo { //here is 24 foo's
                println(42)
            }}}}}}}}}}}}}}}}}}}}}}}}

}
