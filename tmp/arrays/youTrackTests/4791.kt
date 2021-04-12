// Original bug: KT-10322

fun foo(x: Int) {
    val negatives = arrayListOf(1)
    
    run {
        if (x >= 0) {
            System.out?.println(">= 0")
        } else {
            negatives.add(x)
        }
    }
}
