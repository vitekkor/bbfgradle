// Original bug: KT-21520

fun lift(a: Boolean,
         list: MutableList<String>,
         listB: MutableList<String>) {

    if (a) { // Assignment can be lifted out of if
        list += " "
    } else {
        list += listB
    }
}
