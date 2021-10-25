// Original bug: KT-14549

class A {
    val prop: Int
    constructor(arg: Boolean) {
        if (arg) {
            prop = 1
            run { return }
            throw RuntimeException("fail 0")
        }
        prop = 2
    }
}
