// Original bug: KT-30868

operator fun List<Boolean>.get(i1: Int, i2: Int) {}

fun case_25(x: Int?, y: List<Boolean>) {
    while (true) {
        y[break, x!!]
    }
    println(x.inv()) // unsound smartcast to Int, NPE
}
