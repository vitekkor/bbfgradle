// Original bug: KT-12008

import java.util.*

fun foo(o: Optional<String>) {}

class Test(nullable: String?) {
    private val nullableOptional = Optional.ofNullable(nullable)
    fun doIt() {
        foo(nullableOptional)
    }
}
