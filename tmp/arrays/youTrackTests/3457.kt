// Original bug: KT-38853

import kotlin.reflect.*

annotation class Anno(val k: KClass<*>)

class C {
    class Nested
}

interface I {
    @Anno(C.Nested::class)
    fun foo() {}
}
