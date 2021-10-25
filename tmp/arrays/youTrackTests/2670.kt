// Original bug: KT-38705

sealed class Test(val data: Any) {
    object Example : Test("data")
    companion object {
        val data = Example.data
    }
}

fun main() {
    Test.Example
}
