// Original bug: KT-27322

package test

class C : Iterable<String> {
    val list: List<String>

    init {
        for (x in this) println(x) // <-- implicit leaking 'this' in 'for' statement
        list = listOf("a", "b", "c")
    }

    override fun iterator(): Iterator<String> = list.iterator()
}

fun main(args: Array<String>) {
    C()
}

//Exception in thread "main" java.lang.NullPointerException
//    at test.C.iterator(test.kt:11)
//    at test.C.<init>(test.kt:7)
//    at test.TestKt.main(test.kt:15)

