// Original bug: KT-248

fun foo() {
    val i = 1 as Int         //exception
    val b = true as? Boolean //exception
    val j = 1 as Int?        //ok
    val s = "s" as String    //ok
}
