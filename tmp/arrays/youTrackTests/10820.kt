// Original bug: KT-1075

fun foo(b: String) {
    if (b in 1..10) {} //type mismatch
    when (b) { 
        in 1..10 -> 1 //no type mismatch, but it should be here
        else -> 2
    }
}
