// Original bug: KT-30867

fun case_1(x: Boolean?) {
    while (true) {
        for (i in listOf(break, x!!, x!!)) {

        }
    }
    println(x.not()) // unsound smartcast to Boolean, NPE
}
