// Original bug: KT-25063

fun foo(){
    val a: Array<String?> = arrayOf("a")
    val b: Array<String?> = arrayOf("a")
    val list1: List<Array<String?>> = listOf(a, b)
    val list2: List<Array<String?>> = listOf( //fails, found List<Array<String>> instead of List<Array<String?>>
        arrayOf("a"),
        arrayOf("b")
    )
    val c : Array<String?> = arrayOf("c", null)
    val list3: List<Array<String?>> = listOf(a, c)
    val list4: List<Array<String?>> = listOf( //fails, found List<Array<out String?>> instead of List<Array<String?>>
        arrayOf("a"),
        arrayOf("c", null)
    )
}
