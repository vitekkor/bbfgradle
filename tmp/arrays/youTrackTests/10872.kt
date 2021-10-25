// Original bug: KT-245

fun foo() {
    val l = java.util.ArrayList<Int>(2)
    l.add(1)

    //verify error "Expecting to find integer on stack"
    for (el in l) {}

    val iterator = l.iterator()

    //another verify error "Mismatched stack types"
    while (iterator?.hasNext() ?: false) {
        val i = iterator?.next()
    }

    //the same
    if (iterator != null) {
        while (iterator.hasNext()) {
            val i = iterator?.next()
        }
    }

    //this way it works 
    if (iterator != null) {
        while (iterator.hasNext()) {
            iterator.next() //because of the bug KT-244 i can't write "val i = iterator.next()"
        }
    }
}
