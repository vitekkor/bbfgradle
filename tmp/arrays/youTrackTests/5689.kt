// Original bug: KT-19573

class TestAssignmentInArgumentConfusingResolve {
    private var x = 0

    fun setX(xx: Int) {
        notify(x = xx) // In Kotlin: named argument
    }

    private fun notify(x: Int) {}
}
