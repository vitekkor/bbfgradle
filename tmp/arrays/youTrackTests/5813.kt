// Original bug: KT-25318

import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    Foo().bar()
}

class Foo {
    companion object {
        private val DATE_FORMAT = SimpleDateFormat("yyyy/mm/dd hh:mm:ss.SSS z")
    }

    fun bar() {
        println(DATE_FORMAT)
    }
}
