// Original bug: KT-22130


class TestClass(
    // "Safe delete" performed on val1
    private val prop1 : Int = 2
) {
}

fun caller() {
    TestClass(
    // "Safe delete" erroneously deleted prop1
    )
}
