// Original bug: KT-8372

fun main(args : Array<String>) {
    for(i in 10 downTo 0 step 2) {}
    for(i in 1..20 step 2) {}
    
    val p = 1..2
    for(i in p) {}
    
    val p2 = 1..2 step 2
    for(i in p2) {}
}
