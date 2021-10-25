// Original bug: KT-21827

//FILE 1
fun test() {
    run {
        lParams()
    }
}
//FILE 2
//a lot of blank lines























inline fun lParams(width: Int = 1, initParams: () -> Unit = {}) =
        initParams()

