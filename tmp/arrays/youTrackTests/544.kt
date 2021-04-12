// Original bug: KT-42345

object FooKotlin {
    var someProperty: Int = 0
        set(value) {
            field = value
        }
        get() {
            return field
        }
}
