// Original bug: KT-37405

interface Super {
    val x: Int
    val y: Int
}

class Sub(
    override val x: Int,
    override val y: Int
) : Super {
    constructor(i: Int = 0) : this(i, i)
}
