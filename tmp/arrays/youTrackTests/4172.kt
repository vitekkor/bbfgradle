// Original bug: KT-22131

class TestClass(
    val prop1 : Int? = 2
)

fun caller() {
    val list = ArrayList<TestClass>()

    // Start of "Extract method" selection
    list.forEach {
        if (it.prop1 != null) {
            println(it.prop1)
        }
    }
    // End of "Extract method" selection
}
