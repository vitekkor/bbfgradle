// Original bug: KT-40663

import org.intellij.lang.annotations.Language

fun main() {
    foo = "aaa|bbb" // not injected
    bar = "aaa|bbb" // not injected
}

@Language("REGEXP")
var foo = "aaa|bbb"
    set(@Language("REGEXP") value) {
        field = value
    }
@Language("REGEXP")
var bar = "aaa|bbb"
