// Original bug: KT-22130


class TestClass(
    val1 : Int = 1,   // <- Will "Safe Delete" this
    private val prop1 : Int = 2
) {
}

fun caller() {
    TestClass(
        prop1 = 3
    )
}
