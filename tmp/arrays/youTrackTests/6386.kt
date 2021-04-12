// Original bug: KT-30228

private class BinarySearchTree2 {
    fun add(i: Int) {
        TODO("not implemented")
    }

    fun contains(i: Int): Boolean {
        TODO("not implemented")
    }

}

infix fun Boolean.shouldMatch(b:Boolean) = true

class Test {
    init {
        val bst = BinarySearchTree2()
        bst.add(6)
        bst.add(3) // Convert to apply/also is available however below call is ignored
        bst.contains(3) shouldMatch true // Convert to apply/also is not available
    }
}
