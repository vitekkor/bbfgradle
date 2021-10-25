// Original bug: KT-40367

class SomeClass {
    val a1 = IntArray(2) // does NOT work correctly

    val a2: IntArray
    init {
        a2 = IntArray(2) // does NOT work correctly
    }

    val a3: IntArray
    constructor() {
        a3 = IntArray(2) // works correctly
    }
}
