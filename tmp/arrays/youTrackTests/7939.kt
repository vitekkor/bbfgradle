// Original bug: KT-24430

interface SomeInterface

fun main(args: Array<String>) {

    class SomeClass : SomeInterface

    fun <T:SomeInterface?> test(obj:SomeClass?):T = obj as T

    val iAmNull:SomeClass = test<SomeClass>(null)

    println("$iAmNull is null:${iAmNull == null}")

}
