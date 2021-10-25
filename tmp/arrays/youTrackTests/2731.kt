// Original bug: KT-40605

abstract class Workflow<T> {
    inner class Context

    abstract fun render(context: Context)
}

class MyWorkflow : Workflow<Nothing>() {
    override fun render(context: Context) = Unit
}
