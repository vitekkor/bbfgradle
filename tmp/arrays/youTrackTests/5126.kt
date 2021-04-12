// Original bug: KT-34357

import java.util.stream.Collectors

class Foo {
    fun burp(barb: Collection<Int?>): String {
        return barb.stream().map { i: Int? ->
            Integer.toString(i!!)
        }.collect(Collectors.joining())
    }
}
