// Original bug: KT-16412

import java.util.function.Function

object Foo {
    class Requester(val dealToBeOffered: String)
}

class Bar {
    val foo = Function(Foo::Requester)
}
