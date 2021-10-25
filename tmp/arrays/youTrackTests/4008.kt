// Original bug: KT-27606

interface Test

enum class TestImpl1: Test

enum class TestImpl2: Test

fun testFunc(thing: Map<Int, Test>) = ""

fun otherFunc(test1: TestImpl1, test2: TestImpl2, vals: Map<Int, Boolean>) =
    testFunc(vals.mapValues {
        if(it.value)
            test1
        else
            test2
    })
