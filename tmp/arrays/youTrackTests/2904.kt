// Original bug: KT-22270

inline fun l2f1(p: () -> Unit) {}
fun label2simple4() {
    l2f1() { return@l2f1 }
}
fun label2simple5() {
    l2f1() custom@ { return@custom }
}
fun label2simple6() {
//    custom@ l2f1() { return@custom }
} 