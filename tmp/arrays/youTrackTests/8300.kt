// Original bug: KT-22317

class Test {
    init {
        a = 1 // No error, despite of later declaration of 'a'
    }
    constructor() {
        a = 2
    }
    private var a: Int
    init {
        a = 3
    }
}
