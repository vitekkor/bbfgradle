// Original bug: KT-18620

class Test(val weight: Int) {
    var fresh: Boolean? = null

    constructor(weight: Int, fresh: Boolean) : this(weight) {
        this.fresh = fresh
    }
}
