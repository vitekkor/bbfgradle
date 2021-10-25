// Original bug: KT-28706

import java.util.*

class Test {
    internal inner class Id

    private fun m(a: Test2.Id, b: Test2.Id) {
        val x = HashSet<TwoTuple<Test2.Id, Test2.Id>>()
        x.add(TwoTuple(a, b))
    }
}


internal class TwoTuple<A, B>(a: A, b: A)

internal class Test2 {
    internal inner class Id
}
