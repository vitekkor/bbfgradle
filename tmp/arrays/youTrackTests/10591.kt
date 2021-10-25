// Original bug: KT-3820

fun main(args: Array<String>) {
    val list1 = arrayListOf(1..3)
    println(list1[0].start) // VerifyError: trying to call getStart() on Object
    println((list1[0] as IntRange).start) // this workaround

    val list2 = arrayListOf(Thread("foo"))
    println(list2[0].getName()) // works
}
