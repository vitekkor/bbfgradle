// Original bug: KT-31719

import java.io.File

interface A {
    val foo: String
}

open class B : A {
    override val foo = File("").name
}

class C : B() {
    override val foo: String? = null
}
