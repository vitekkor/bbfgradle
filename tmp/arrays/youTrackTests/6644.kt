// Original bug: KT-29786

data class SomeDataClass(var reference: SomeDataClass?)

fun main() {
    val a = SomeDataClass(null)

    // so far so good
    println(a)
    a.reference = a

    // this will cause StackOverflowError
    println(a)

    // what even worse this will fail as well
    val someMap = mutableSetOf<SomeDataClass>()
    someMap.add(a)
}
