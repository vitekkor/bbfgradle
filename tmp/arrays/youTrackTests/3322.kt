// Original bug: KT-39135

class TestProperty {
    var age: Int
        set(value) {
            field = if (value > 0) value else 0
        }

    constructor(age: Int) {
        this.age = age
    }

    override fun toString(): String {
        return "$age"
    }
}
