// Original bug: KT-5605

import Foo.Bar

class Foo {
    class Bar
}

fun Foo.foo() {
    //without import an error "Nested class accessed via instance reference" is reported
    val b = Bar()    
}
