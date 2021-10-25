// Original bug: KT-24528

fun some() {
    getB().test()
    // test() after fix won't print "getB" anymore
}

class B
fun getB(): B {
    println("getB")
    return B()
}

fun B.test() {}
