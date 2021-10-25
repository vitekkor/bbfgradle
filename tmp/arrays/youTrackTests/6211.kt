// Original bug: KT-27758

class MyTest {
    fun contract(block: () -> Unit) {

    }

    fun test() {
        contract {
//      ^^^^^^^^ - Error in contract description: Error in contract description
        }
    }
}
