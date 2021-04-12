// Original bug: KT-27954

data class Test(val foo: Int, val bar: String, val baz: Int) {
    override fun toString(): String {
        return "Test(foo=$foo, bar='$bar', baz=$baz)"
    }
}
