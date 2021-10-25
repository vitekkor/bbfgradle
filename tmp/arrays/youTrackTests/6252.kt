// Original bug: KT-30876

fun foo() {
    val x: Any? = null

    val g = x as? String
    assert(g != null) {
        "e"
    } // Assert should be replaced to operator reported 

}
