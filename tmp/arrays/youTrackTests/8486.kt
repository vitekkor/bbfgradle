// Original bug: KT-20846

class Bug {
    private var x: Int = 0

    fun onCreate() {
        Problem()
    }

    abstract class AbstractProblem(action: () -> Unit)

    inner class Problem : AbstractProblem({ x++ })
}
